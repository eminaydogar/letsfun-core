package com.easoft.letsfun.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String errorCode;
	protected String errorDetail;
	protected Throwable error;
	
	
	public ServiceOperationException() {
		
	}

	public ServiceOperationException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	public ServiceOperationException(String message, String errorCode,String errorDetail) {
		super(message);
		this.errorCode = errorCode;
		this.errorDetail = errorDetail;
	}

	public ServiceOperationException(String message, String errorCode, Throwable error) {
		super(message);
		this.errorCode = errorCode;
		this.error = error;
	}

	public ServiceOperationException(String message) {
		super(message);
	}
	

}
