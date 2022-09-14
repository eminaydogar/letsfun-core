package com.easoft.letsfun.common.api.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String title;

	private String content;

	private Long capacity;

	private ObjectValueBean type;

	private byte[] advertImage;

	private String status;

	private Date cdate;

	private Date edate;

	private UserBean createdUser;

	private ActivityDetailBean details;

}
