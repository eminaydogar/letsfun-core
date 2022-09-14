package com.easoft.letsfun.service.core;

import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.api.request.login.LoginControlRequest;
import com.easoft.letsfun.common.api.request.login.RegisterRequest;
import com.easoft.letsfun.common.api.request.login.RegisterVerifyRequest;
import com.easoft.letsfun.common.api.response.LoginControlResponse;
import com.easoft.letsfun.common.api.response.RegisterResponse;
import com.easoft.letsfun.common.api.response.RegisterVerifyResponse;

public interface LoginFrontService {
	
	public BaseResponse<LoginControlResponse> loginControl(LoginControlRequest request);
	
	public BaseResponse<RegisterResponse> register(RegisterRequest request);

	public BaseResponse<RegisterVerifyResponse> registerVerify(RegisterVerifyRequest request);

}
