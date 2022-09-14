package com.easoft.letsfun.common.api.request.user;

import javax.validation.constraints.NotNull;

import com.easoft.letsfun.common.api.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteAccountRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long userId;

	@NotNull
	private String password;

}
