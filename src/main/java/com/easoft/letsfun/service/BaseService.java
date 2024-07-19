package com.easoft.letsfun.service;

import com.easoft.letsfun.common.dto.UserDto;


public abstract class BaseService {

	public void handleError(Exception exp) {

	}

	public boolean isVerifiedUser(UserDto user) {
		return "Y".equalsIgnoreCase(user.getVertify());
	}
	
}
