package com.easoft.letsfun.common.api;

import java.util.Date;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.exception.ServiceOperationException;

import lombok.Data;

@Data
public class BaseResponse<T> {
	private String resultCode;
	private String message;
	private Date time;
	private T response;

	public BaseResponse() {
	}

	public BaseResponse<T> handle(T response, String resultCode, Exception exception) {
		this.response = response;
		this.resultCode = resultCode;
		this.message = exception.getMessage();
		return this;
	}

	public void setFaildResponse(Exception exception) {

		this.resultCode = CacheConstant.SYSTEM_ERROR.getCode();
		this.time = new Date();
		this.response = null;

		// || e instanceof RequestRejectedException
		if (exception instanceof ServiceOperationException) {
			ServiceOperationException opExp = (ServiceOperationException) exception;
			this.message = opExp.getMessage();
			this.resultCode = opExp.getErrorCode();
		}

	}

	public void setSuccessResponse(T response) {
		this.resultCode = CacheConstant.SUCCESS.getCode();
		this.message = CacheConstant.SUCCESS.getValue();
		this.time = new Date();
		this.response = response;

	}

	public void clear() {
		resultCode = null;
		message = null;
		response = null;
	}

}
