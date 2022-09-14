package com.easoft.letsfun.common.api.request.user;

import javax.validation.constraints.NotNull;

import com.easoft.letsfun.common.api.BaseRequest;

import lombok.Getter;
import lombok.Setter;


public class UserActivityInfoRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Getter
	@Setter
	private Long userId;
	

	
	
}
