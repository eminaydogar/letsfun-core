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
		id = entity.getId();
		content = entity.getContent();
		cdate = entity.getCdate();
		status = entity.getStatus();

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

	@Override
	public void setLazyClass(BaseEntity baseEntity) {
		if (baseEntity instanceof UserDefinition) {
			user = new UserDto((UserDefinition) baseEntity);
		} else if (baseEntity instanceof ActivityDefinition) {
			activity = new ActivityDto((ActivityDefinition) baseEntity);
		}

	}

}
