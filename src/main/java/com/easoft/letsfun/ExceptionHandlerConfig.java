package com.easoft.letsfun;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.exception.AuthorizationException;
import com.easoft.letsfun.common.exception.FirewallException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

	// other exception handlers

	@ExceptionHandler(value = AuthorizationException.class)
	@ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
	@ResponseBody
	public ResponseEntity<BaseResponse<?>> authException(final AuthorizationException ex,
			final HttpServletResponse response) {
		
		BaseResponse<?> baseResponse = new BaseResponse<>();
		baseResponse.setFaildResponse(ex);
		ResponseEntity<BaseResponse<?>> entity = new ResponseEntity<BaseResponse<?>>(baseResponse,
				HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);

		return entity;
	}
	
	@ExceptionHandler(value = FirewallException.class)
	@ResponseStatus(HttpStatus.PROXY_AUTHENTICATION_REQUIRED)
	@ResponseBody
	public ResponseEntity<BaseResponse<?>> firewallException(final FirewallException ex,
			final HttpServletResponse response) {
		
		BaseResponse<?> baseResponse = new BaseResponse<>();
		baseResponse.setFaildResponse(ex);
		ResponseEntity<BaseResponse<?>> entity = new ResponseEntity<BaseResponse<?>>(baseResponse,
				HttpStatus.PROXY_AUTHENTICATION_REQUIRED);

		return entity;
	}
	
	@ExceptionHandler(value = AuthenticationServiceException.class)
	@ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
	@ResponseBody
	public ResponseEntity<BaseResponse<?>> basicAuthSecureException(final AuthenticationServiceException ex,
			final HttpServletResponse response) {
		
		BaseResponse<?> baseResponse = new BaseResponse<>();
		baseResponse.setFaildResponse(ex);
		ResponseEntity<BaseResponse<?>> entity = new ResponseEntity<BaseResponse<?>>(baseResponse,
				HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);

		return entity;
	}
	
	
	

}
