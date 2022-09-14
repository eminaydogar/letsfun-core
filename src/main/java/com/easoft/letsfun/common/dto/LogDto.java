package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.LogDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String methodName;

	private Long processType;

	private String resultCode;

	private String resultExplanation;

	private String clientIp;

	private Date cdate;

	public LogDto() {

	}

	public LogDto(LogDefinition entity) {
		id = entity.getId();
		methodName = entity.getMethodName();
		processType = entity.getProcessType();
		resultCode = entity.getResultCode();
		resultExplanation = entity.getResultExplanation();
		clientIp = entity.getClientIp();
		cdate = entity.getCdate();
	}

	@Override
	public LogDefinition copyToEntity(BaseEntity entity) {
		LogDefinition model = (LogDefinition) entity;
		model.setId(id);
		model.setMethodName(methodName);
		model.setProcessType(processType);
		model.setResultCode(resultCode);
		model.setResultExplanation(resultExplanation);
		model.setClientIp(clientIp);
		model.setCdate(cdate);
		return model;
	}

}
