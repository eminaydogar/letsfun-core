package com.easoft.letsfun.service.domain;

import java.util.List;

import com.easoft.letsfun.common.dto.DocumentContentDto;
import com.easoft.letsfun.entity.DocumentContentDefinition;

public interface DocumentContentService {
	
	public DocumentContentDefinition getDocumentContentById(Long id);
	
	public DocumentContentDefinition getDocumentContentByType(Long typeId);
	
	public List<DocumentContentDefinition> getAllDocumentContent();
	
	public DocumentContentDefinition save(DocumentContentDto dto);
	
	public DocumentContentDefinition update(DocumentContentDto dto);

}
