package com.easoft.letsfun.common.api.request.user;

import javax.validation.constraints.NotNull;

import com.easoft.letsfun.common.api.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "User id not be null")
	private Long userId;
	
	private String password;
	
	private String newPassword;
	
	private String email;
	
	private Long phoneNumber;
	
	private byte[] image;
	
	private String address;
	
	private boolean isPasswordChange;

}
