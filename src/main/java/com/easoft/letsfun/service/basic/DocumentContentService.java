package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.DocumentContentDto;

public interface DocumentContentService {
	
	public DocumentContentDto getDocumentContentById(Long id);
	
	public DocumentContentDto getDocumentContentByType(Long typeId);
	
	public List<DocumentContentDto> getAllDocumentContent();
	
	public DocumentContentDto save(DocumentContentDto dto);
	
	public DocumentContentDto update(DocumentContentDto dto);

}
