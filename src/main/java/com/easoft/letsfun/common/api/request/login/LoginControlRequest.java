package com.easoft.letsfun.common.api.request.login;

import javax.validation.constraints.NotEmpty;

import com.easoft.letsfun.common.api.BaseRequest;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginControlRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message = "Kullanıcı adı boş olamaz")
	private String username;
	
	@NotEmpty(message = "Şifre boş olamaz")
	private String password;

}
