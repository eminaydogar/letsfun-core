package com.easoft.letsfun.common.api.request.login;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.easoft.letsfun.common.api.BaseRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVerifyRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String username;

	@NotEmpty
	@Length(max = 10)
	private String verifyCode;

}
