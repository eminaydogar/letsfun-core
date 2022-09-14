package com.easoft.letsfun.common.api.response;

import java.io.Serializable;

import com.easoft.letsfun.common.api.bean.UserBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserBean user;

	private boolean passwordUpdated;
}
