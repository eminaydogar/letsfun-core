package com.easoft.letsfun.service.basic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.DocumentContentDto;
import com.easoft.letsfun.entity.DocumentContentDefinition;
import com.easoft.letsfun.repository.DocumentContentRepository;
import com.easoft.letsfun.service.basic.DocumentContentService;

@Transactional
@Service
public class DocumentServiceImpl implements DocumentContentService {

	@Autowired
	DocumentContentRepository repository;

	@Override
	public DocumentContentDto getDocumentContentById(Long id) {

		DocumentContentDto result = null;

		DocumentContentDefinition entity = null;

		try {
			entity = repository.findById(id).orElse(null);
			if (entity != null) {
				result = new DocumentContentDto(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public DocumentContentDto getDocumentContentByType(Long typeId) {
		DocumentContentDto result = null;

		DocumentContentDefinition entity = null;

		try {
			entity = repository.findByDocumentType(typeId);
			if (entity != null) {
				result = new DocumentContentDto(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public List<DocumentContentDto> getAllDocumentContent() {
		List<DocumentContentDto> result = null;

		List<DocumentContentDefinition> entityList = null;

		try {
			entityList = repository.findAll();
			if (entityList != null && !entityList.isEmpty()) {
				result = new ArrayList<>();
				for (DocumentContentDefinition entity : entityList) {
					result.add(new DocumentContentDto(entity));
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public DocumentContentDto save(DocumentContentDto dto) {

		DocumentContentDto result = null;

		DocumentContentDefinition resultEntity = null;

		try {
			resultEntity = repository.save(dto.copyToEntity(new DocumentContentDefinition()));
			if (resultEntity != null) {
				result = new DocumentContentDto(resultEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public DocumentContentDto update(DocumentContentDto dto) {

		DocumentContentDto result = null;

		DocumentContentDefinition resultEntity = null;

		try {
			resultEntity = repository.findById(dto.getId()).orElse(null);

			if (resultEntity != null) {
				resultEntity = repository.saveAndFlush(dto.copyToEntity(resultEntity));
				result = new DocumentContentDto(resultEntity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

}
