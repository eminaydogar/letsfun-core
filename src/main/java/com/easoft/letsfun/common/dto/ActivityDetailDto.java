package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.ActivityDetail;
import com.easoft.letsfun.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDetailDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long ticketContentId;

	private Long participantsNumber;

	private Date cdate;

	private String featureValues;
	
	private String address;
	
	private String addressDetail;

	public ActivityDetailDto() {

	}

	public ActivityDetailDto(ActivityDetail entity) {
		id = entity.getId();
		ticketContentId = entity.getTicketContentId();
		participantsNumber = entity.getParticipantsNumber();
		cdate = entity.getCdate();
		featureValues = entity.getFeatureValues();
		address=entity.getAddress();
		addressDetail = entity.getAddressDetail();

	}

	@Override
	public ActivityDetail copyToEntity(BaseEntity entity) {
		ActivityDetail model = (ActivityDetail) entity;
		model.setId(id);
		model.setParticipantsNumber(participantsNumber);
		model.setTicketContentId(ticketContentId);
		model.setFeatureValues(featureValues);
		model.setCdate(cdate);
		model.setAddress(address);
		model.setAddressDetail(addressDetail);
		return model;
	}

	@Override
	public void setLazyClass(BaseEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
