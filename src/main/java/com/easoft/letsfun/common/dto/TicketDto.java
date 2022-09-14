package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.TicketDefinition;
import com.easoft.letsfun.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private UserDto user;

	private ActivityDto activity;

	private String content;

	private Date cdate;

	private String status;

	public TicketDto() {

	}

	public TicketDto(TicketDefinition entity) {
		setter(entity);
		convertEntityToDto(entity.getActivity());
		convertEntityToDto(entity.getUser());
	}

	public TicketDto withUser(TicketDefinition entity) {
		setter(entity);
		convertEntityToDto(entity.getUser());
		return this;
	}

	public TicketDto withActivity(TicketDefinition entity) {
		setter(entity);
		convertEntityToDto(entity.getActivity());
		return this;
	}

	public TicketDto single(TicketDefinition entity) {
		setter(entity);
		return this;
	}

	private void setter(TicketDefinition entity) {
		id = entity.getId();
		content = entity.getContent();
		status = entity.getStatus();
		cdate = entity.getCdate();
	}

	private void convertEntityToDto(ActivityDefinition entity) {
		activity = new ActivityDto().single(entity);
	}

	private void convertEntityToDto(UserDefinition entity) {
		user = new UserDto().single(entity);
	}

	@Override
	public TicketDefinition copyToEntity(BaseEntity entity) {
		TicketDefinition model = (TicketDefinition) entity;
		model.setId(id);
		model.setContent(content);
		if (activity != null) {
			model.setActivity(activity.copyToEntity(new ActivityDefinition()));
		}
		if (user != null) {
			model.setUser(user.copyToEntity(new UserDefinition()));
		}
		model.setCdate(cdate);
		model.setStatus(status);

		return model;
	}

}
