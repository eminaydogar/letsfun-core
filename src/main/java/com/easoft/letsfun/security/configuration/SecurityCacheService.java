package com.easoft.letsfun.security.configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.easoft.letsfun.security.IpAdressModel;

public final class SecurityCacheService {

	private static Map<String, IpAdressModel> ipAdress = new HashMap<>();

	public static void setIpAddress(ServletRequest request) {
		
		IpAdressModel ipModel = ipAdress.get(request.getRemoteAddr());
		if (ipModel == null) {
			ipModel = new IpAdressModel();
			ipModel.setIpAddress(request.getRemoteAddr());
			ipModel.setLastRequestTime(new Date());
			ipModel.setRequestCount(1);
		} else {
			ipModel.setLastRequestTime(new Date());
			ipModel.setRequestCount(ipModel.getRequestCount() + 1);
		}

		ipAdress.put(ipModel.getIpAddress(), ipModel);

	}

	public static IpAdressModel getIpAddress(ServletRequest request) {
		return ipAdress.get(request.getRemoteAddr());

	}

}
