package com.easoft.letsfun.common.api.response;

import java.io.Serializable;
import java.util.List;

import com.easoft.letsfun.common.api.bean.ActivityBean;
import com.easoft.letsfun.common.api.bean.ActivityJoinRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivityInfoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	private List<ActivityBean> createdActivities;
	private List<ActivityJoinRequestBean> joinRequestActivities;

}
