package com.easoft.letsfun.common.api.request.login;

import javax.validation.constraints.NotEmpty;

import com.easoft.letsfun.common.api.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String address;
	
	private Long phoneNumber;
	
	private byte[] image;

}
