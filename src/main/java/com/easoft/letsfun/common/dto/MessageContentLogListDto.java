package com.easoft.letsfun.common.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.MessageContentLogList;
import com.easoft.letsfun.entity.UserDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageContentLogListDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long channelType;

	private Long messageType;

	private String messageContent;

	private UserDto relatedUser;

	private String status;

	private Date cdate;

	private Date edate;

	@Override
	public MessageContentLogList copyToEntity(BaseEntity entity) {
		MessageContentLogList model = (MessageContentLogList) entity;

		model.setId(id);
		model.setCdate(cdate);
		model.setChannelType(channelType);
		model.setEdate(edate);
		model.setMessageContent(messageContent);
		model.setMessageType(messageType);
		if (relatedUser != null) {
			model.setRelatedUser(relatedUser.copyToEntity(new UserDefinition()));
		}
		model.setStatus(status);

		return model;
	}

	public MessageContentLogListDto(MessageContentLogList entity) {
		BeanUtils.copyProperties(entity, this, "relatedUser");
	}

	public MessageContentLogListDto() {

	}

	@Override
	public void setLazyClass(BaseEntity baseEntity) {
		if (baseEntity instanceof UserDefinition) {
			relatedUser = new UserDto((UserDefinition) baseEntity);
		}

	}

}
