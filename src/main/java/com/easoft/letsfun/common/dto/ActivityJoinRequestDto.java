package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.ActivityJoinRequest;
import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

public class ActivityJoinRequestDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private ActivityDto activity;

	@Getter
	@Setter
	private String requestStatus;

	@Getter
	@Setter
	private UserDto user;

	@Getter
	@Setter
	private String rejectReason;

	@Getter
	@Setter
	private Date cdate;
	
	public ActivityJoinRequestDto () {
		
	}

	public ActivityJoinRequestDto(ActivityJoinRequest entity) {
		id = entity.getId();
		requestStatus = entity.getRequestStatus();
		rejectReason = entity.getRejectReason();
		cdate = entity.getCdate();
	}

	@Override
	public void setLazyClass(BaseEntity baseEntity) {

		if (baseEntity instanceof UserDefinition) {
			user = new UserDto((UserDefinition) baseEntity);
		} else if (baseEntity instanceof ActivityDefinition) {
			activity = new ActivityDto((ActivityDefinition) baseEntity);
		}

	}

	@Override
	public ActivityJoinRequest copyToEntity(BaseEntity entity) {
		ActivityJoinRequest model = (ActivityJoinRequest) entity;
		model.setId(id);
		model.setRejectReason(rejectReason);
		model.setRequestStatus(requestStatus);
		model.setCdate(cdate);
		if (activity != null) {
			model.setActivity(activity.copyToEntity(new ActivityDefinition()));
		}
		if (user != null) {
			model.setUser(user.copyToEntity(new UserDefinition()));
		}
		return model;
	}

}
