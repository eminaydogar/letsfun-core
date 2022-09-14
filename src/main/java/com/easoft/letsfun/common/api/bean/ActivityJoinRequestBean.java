package com.easoft.letsfun.common.api.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityJoinRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private ActivityBean activity;
	private String requestStatus;
	private String rejectReason;
	private Date cdate;
	private UserBean user;

}
