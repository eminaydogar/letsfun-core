package com.easoft.letsfun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easoft.letsfun.entity.DocumentContentDefinition;

@Repository
public interface DocumentContentRepository extends JpaRepository<DocumentContentDefinition, Long> {

	public DocumentContentDefinition findByDocumentType(Long typeId);

}
