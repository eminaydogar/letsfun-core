package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.ActivityDetail;
import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String title;

	private String content;

	private Long capacity;

	private Long typeId;

	private byte[] advertImage;

	private String status;

	private Date cdate;

	private Date edate;

	private UserDto createdUser;

	private ActivityDetailDto details;

	private void setter(ActivityDefinition entity) {
		id = entity.getId();
		title = entity.getTitle();
		content = entity.getContent();
		capacity = entity.getCapacity();
		typeId = entity.getTypeId();
		advertImage = entity.getAdvertImage();
		status = entity.getStatus();
		cdate = entity.getCdate();
		edate = entity.getEdate();
		convertEntityToDto(entity.getDetails());
	}

	public ActivityDto() {

	}

	public ActivityDto(ActivityDefinition entity) {
		setter(entity);
		convertEntityToDto(entity.getCreatedUser());
		convertEntityToDto(entity.getDetails());
	}

	public ActivityDto single(ActivityDefinition entity) {
		setter(entity);
		return this;
	}

	public ActivityDto withUser(ActivityDefinition entity) {
		setter(entity);
		convertEntityToDto(entity.getCreatedUser());
		return this;
	}

	private void convertEntityToDto(UserDefinition user) {
		createdUser = new UserDto().single(user);
	}

	private void convertEntityToDto(ActivityDetail activityDetail) {
		details = new ActivityDetailDto(activityDetail);
	}

	@Override
	public ActivityDefinition copyToEntity(BaseEntity entity) {
		ActivityDefinition model = (ActivityDefinition) entity;
		model.setId(id);
		model.setTypeId(typeId);
		model.setTitle(title);
		model.setContent(content);
		model.setCapacity(capacity);
		model.setAdvertImage(advertImage);
		if (createdUser != null) {
			model.setCreatedUser(createdUser.copyToEntity(new UserDefinition()));
		}
		if (details != null) {
			model.setDetails(details.copyToEntity(new ActivityDetail()));
		}
		model.setStatus(status);
		model.setCdate(cdate);
		model.setEdate(edate);

		return model;
	}
	

}
