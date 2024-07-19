package com.easoft.letsfun.service.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.DocumentContentDto;
import com.easoft.letsfun.entity.DocumentContentDefinition;
import com.easoft.letsfun.repository.DocumentContentRepository;
import com.easoft.letsfun.service.domain.DocumentContentService;

@Transactional
@Service
public class DocumentServiceImpl implements DocumentContentService {

	@Autowired
	DocumentContentRepository repository;

	@Override
	public DocumentContentDefinition getDocumentContentById(Long id) {


		DocumentContentDefinition entity = null;

		try {
			entity = repository.findById(id).orElse(null);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return entity;
	}

	@Override
	public DocumentContentDefinition getDocumentContentByType(Long typeId) {
	

		DocumentContentDefinition entity = null;

		try {
			entity = repository.findByDocumentType(typeId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return entity;
	}

	@Override
	public List<DocumentContentDefinition> getAllDocumentContent() {
	

		List<DocumentContentDefinition> entityList = null;

		try {
			entityList = repository.findAll();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return entityList;
	}

	@Override
	public DocumentContentDefinition save(DocumentContentDto dto) {


		DocumentContentDefinition resultEntity = null;

		try {
			resultEntity = repository.save(dto.copyToEntity(new DocumentContentDefinition()));
		
		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntity;
	}

	@Override
	public DocumentContentDefinition update(DocumentContentDto dto) {

		DocumentContentDefinition resultEntity = null;

		try {
			resultEntity = repository.findById(dto.getId()).orElse(null);

			if (resultEntity != null) {
				resultEntity = repository.saveAndFlush(dto.copyToEntity(resultEntity));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntity;
	}

}
