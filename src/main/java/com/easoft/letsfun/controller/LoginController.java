package com.easoft.letsfun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.api.request.login.LoginControlRequest;
import com.easoft.letsfun.common.api.request.login.RegisterRequest;
import com.easoft.letsfun.common.api.request.login.RegisterVerifyRequest;
import com.easoft.letsfun.common.api.response.LoginControlResponse;
import com.easoft.letsfun.common.api.response.RegisterResponse;
import com.easoft.letsfun.common.api.response.RegisterVerifyResponse;
import com.easoft.letsfun.service.core.LoginFrontService;

@RestController
@RequestMapping(value = "api/login")
public class LoginController {

	@Autowired
	private LoginFrontService loginFrontService;


	@PostMapping(value = "/login-control")
	public BaseResponse<LoginControlResponse> loginControl(@Valid @RequestBody LoginControlRequest request) {
		return loginFrontService.loginControl(request);
	}

	@PostMapping(value = "/register")
	public BaseResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
		return loginFrontService.register(request);
	}
	
	@PostMapping(value = "/register-verify")
	public BaseResponse<RegisterVerifyResponse> registerVerify(@Valid @RequestBody RegisterVerifyRequest request) {
		return loginFrontService.registerVerify(request);
	}

}
