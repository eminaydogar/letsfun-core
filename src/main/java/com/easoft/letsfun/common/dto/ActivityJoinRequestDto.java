package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.ActivityJoinRequest;
import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityJoinRequestDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private ActivityDto activity;

	private String requestStatus;

	private UserDto user;

	private String rejectReason;

	private Date cdate;

	public ActivityJoinRequestDto() {

	}

	public ActivityJoinRequestDto(ActivityJoinRequest entity) {
		setter(entity);
		convertEntityToDto(entity.getActivity());
		convertEntityToDto(entity.getUser());
	}

	public ActivityJoinRequestDto withUser(ActivityJoinRequest entity) {
		setter(entity);
		convertEntityToDto(entity.getUser());
		return this;
	}

	public ActivityJoinRequestDto withActivity(ActivityJoinRequest entity) {
		setter(entity);
		convertEntityToDto(entity.getActivity());
		return this;
	}

	public ActivityJoinRequestDto single(ActivityJoinRequest entity) {
		setter(entity);
		return this;
	}

	private void setter(ActivityJoinRequest entity) {
		id = entity.getId();
		requestStatus = entity.getRequestStatus();
		rejectReason = entity.getRejectReason();
		cdate = entity.getCdate();
	}

	private void convertEntityToDto(ActivityDefinition entity) {
		activity = new ActivityDto().single(entity);
	}

	private void convertEntityToDto(UserDefinition entity) {
		user = new UserDto().single(entity);
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
