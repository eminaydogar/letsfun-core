package com.easoft.letsfun.common.dto;

import java.util.Date;

import com.easoft.letsfun.entity.BaseEntity;
import com.easoft.letsfun.entity.DocumentContentDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentContentDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long documentType;

	private String content;

	private Date cdate;

	private String status;

	public DocumentContentDto() {

	}

	public DocumentContentDto(DocumentContentDefinition entity) {
		id = entity.getId();
		documentType = entity.getDocumentType();
		content = entity.getContent();
		cdate = entity.getCdate();
		status = entity.getStatus();
	}

	@Override
	public DocumentContentDefinition copyToEntity(BaseEntity entity) {
		DocumentContentDefinition model = (DocumentContentDefinition) entity;
		model.setId(id);
		model.setCdate(cdate);
		model.setDocumentType(documentType);
		model.setStatus(status);
		model.setContent(content);
		return model;

	}

	@Override
	public void setLazyClass(BaseEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
