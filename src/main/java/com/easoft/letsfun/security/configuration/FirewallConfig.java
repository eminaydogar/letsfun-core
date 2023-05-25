package com.easoft.letsfun.security.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.filter.GenericFilterBean;

import com.easoft.letsfun.cache.CacheManager;
import com.easoft.letsfun.cache.ObjectValueTypeConstant;
import com.easoft.letsfun.common.exception.FirewallException;
import com.easoft.letsfun.common.exception.ServiceOperationException;
import com.easoft.letsfun.entity.resultset.ObjectValue;
import com.easoft.letsfun.security.IpAdressModel;
import com.easoft.letsfun.service.basic.LoggerService;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FirewallConfig extends GenericFilterBean {

	@Autowired
	private LoggerService loggerService;

	@Bean
	public HttpFirewall getHttpFirewall() {
		StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
		strictHttpFirewall.setAllowSemicolon(true);
		strictHttpFirewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT"));
		strictHttpFirewall.setAllowedHostnames(hostName -> getUrlSecureList().contains(hostName));
		return strictHttpFirewall;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {

			System.out.println("--------FILTERCHAIN---------");
			chain.doFilter(req, res);
			
		} catch (RequestRejectedException e) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;

			log.warn("request_rejected: remote={}, user_agent={}, request_url={}", request.getRemoteHost(),
					request.getHeader(HttpHeaders.USER_AGENT), request.getRequestURL(), e);

			response.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);

			loggerService.saveError(666L, "FirewallConfig.doFilter", e, request.getRemoteHost());

			throw new FirewallException("Request rejected : " + e.getMessage());
		} 
	}

	private List<String> getUrlSecureList() {
		CacheManager.load();
		List<String> urlSecureList = new ArrayList<>();
		List<ObjectValue> values = CacheManager.getItemsByObjectType(ObjectValueTypeConstant.SECURE_HOST);
		if (values != null && !values.isEmpty()) {
			for (ObjectValue value : values) {
				urlSecureList.add(value.getObjectName());
			}
		}
		return urlSecureList;
	}

	private void ipControl(ServletRequest req) throws ServletException {

		IpAdressModel model = SecurityCacheService.getIpAddress(req);
		if (model == null) {
			SecurityCacheService.setIpAddress(req);
		} else {

			if (model.getRequestCount() >= 5) {
				long seconds = (new Date().getTime() - model.getLastRequestTime().getTime()) / 1000;
				if (seconds < 10)
					throw new ServletException("Remote Address Limit Exception");
			}

			else
				SecurityCacheService.setIpAddress(req);
		}

	}
}
