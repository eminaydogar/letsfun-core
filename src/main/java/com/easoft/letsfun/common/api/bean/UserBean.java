package com.easoft.letsfun.common.api.bean;

import java.io.Serializable;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String username;

	private String email;

	private AddressBean address;

	private Long phoneNumber;

	private String vertify;
	
	private String status;

	private byte[] image;

}
