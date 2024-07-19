package com.easoft.letsfun.service.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.common.Constants.RECORD_STATUS;
import com.easoft.letsfun.common.dto.ActivityDto;
import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.repository.ActivityRepositoriy;
import com.easoft.letsfun.service.domain.ActivityService;

@Service
public class ActivityServiceImpl extends BaseDomainService implements ActivityService {

	@Autowired
	private ActivityRepositoriy repo;

	@Override
	public ActivityDefinition getActivityById(Long id) {

		ActivityDefinition activity = null;
		try {
			activity = repo.findById(1L).orElse(null);

		} catch (Exception e) {

		}

		return activity;
	}

	@Override
	public List<ActivityDefinition> getActiveActivityList() {
		List<ActivityDefinition> activityList = null;
		try {
			activityList = repo.findByStatus("Y");

		} catch (Exception e) {

		}

		return activityList;
	}

	@Override
	public ActivityDefinition save(ActivityDto dto) {

		ActivityDefinition activityDefinition = null;

		try {
			activityDefinition = repo.save(dto.copyToEntity(new ActivityDefinition()));

		} catch (Exception e) {

		}
		return activityDefinition;
	}

	@Override
	public ActivityDefinition update(ActivityDto dto) {

		ActivityDefinition entity = null;

		try {
			entity = repo.findById(dto.getId()).orElse(null);

			if (entity != null) {
				entity = dto.copyToEntity(entity);
				entity = repo.saveAndFlush(entity);
			}

		} catch (Exception e) {

		}
		return entity;
	}

	@Override
	public List<ActivityDefinition> getActivityListByCreatedUserId(Long userId) {

		List<ActivityDefinition> activityList = null;

		try {

			activityList = repo.findByCreatedUserId(userId);

		} catch (Exception e) {

		}

		return activityList;
	}

	@Override
	public List<ActivityDefinition> getActiveActivityListByCreatedUserId(Long id) {
		
		List<ActivityDefinition> activityList = null;

		try {
			activityList = repo.findByCreatedUserIdAndStatus(id,RECORD_STATUS.ACTIVE);
		} catch (Exception e) {

		}

		return activityList;
	}

}
