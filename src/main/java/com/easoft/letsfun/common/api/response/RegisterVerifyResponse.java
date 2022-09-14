package com.easoft.letsfun.common.api.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterVerifyResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isSuccess;
	private Date processDate;
	private String verifyUserName;

}
