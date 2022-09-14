package com.easoft.letsfun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easoft.letsfun.entity.ActivityDefinition;

@Repository
public interface ActivityRepositoriy extends JpaRepository<ActivityDefinition, Long> {

	public List<ActivityDefinition> findByStatus(String status);

	@Query(value = "select a from ActivityDefinition a where a.createdUser.id=?1")
	public List<ActivityDefinition> findByCreatedUserId(Long userId);

}
