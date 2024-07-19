package com.easoft.letsfun.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easoft.letsfun.common.dto.LogDto;
import com.easoft.letsfun.service.domain.LoggerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@WebFilter("/*")
public class LogFilter implements Filter {
	
	@Autowired
	private LoggerService loggerService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		long time = System.currentTimeMillis();
		LogDto logDto = new LogDto();
		logDto.setCdate(new Date());
		
		try {
			chain.doFilter(req, resp);
			logDto.setClientIp(req.getRemoteAddr());
			logDto.setMethodName(((HttpServletRequest)req).getRequestURI());
			
		} finally {
			
			time = System.currentTimeMillis() - time;
			logDto.setEdate(new Date());
			logDto.setElapsedTime(time);
	
			loggerService.save(logDto);
			
			System.out.println("{}: {} ms "+ ((HttpServletRequest) req).getRequestURI()+": "+ time);
		}
	}

}
