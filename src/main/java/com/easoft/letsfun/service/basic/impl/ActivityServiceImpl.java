package com.easoft.letsfun.service.basic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.ActivityDto;
import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.repository.ActivityRepositoriy;
import com.easoft.letsfun.service.basic.ActivityService;

@Transactional
@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepositoriy repo;

	@Override
	public ActivityDto getActivityWithUserById(Long id) {
		ActivityDto result = null;
		ActivityDefinition activity = null;
		try {
			activity = repo.findById(1L).orElse(null);
			if (activity != null) {
				result = new ActivityDto().withUser(activity);
			}
		} catch (Exception e) {

		}

		return result;
	}

	@Override
	public ActivityDto getActivityById(Long id) {
		ActivityDto result = null;
		ActivityDefinition activity = null;
		try {
			activity = repo.findById(1L).orElse(null);
			if (activity != null) {
				result = new ActivityDto().single(activity);
			}
		} catch (Exception e) {

		}

		return result;
	}

	@Override
	public List<ActivityDto> getActiveActivityList() {
		List<ActivityDto> result = null;
		List<ActivityDefinition> activityList = null;
		try {
			activityList = repo.findByStatus("Y");
			if (activityList != null && !activityList.isEmpty()) {
				result = new ArrayList<>();
				for (ActivityDefinition entity : activityList) {
					result.add(new ActivityDto().single(entity));
				}
			}
		} catch (Exception e) {

		}

		return result;
	}

	@Override
	public ActivityDto save(ActivityDto dto) {

		ActivityDefinition activityDefinition = null;
		ActivityDto result = null;

		try {
			activityDefinition = repo.save(dto.copyToEntity(new ActivityDefinition()));

			if (activityDefinition != null) {
				result = new ActivityDto().single(activityDefinition);
			}

		} catch (Exception e) {

		}
		return result;
	}

	@Override
	public ActivityDto update(ActivityDto dto) {
		ActivityDefinition entity = null;
		ActivityDto result = null;

		try {
			entity = repo.findById(dto.getId()).orElse(null);

			if (entity != null) {
				entity = dto.copyToEntity(entity);
				entity = repo.saveAndFlush(entity);
				result = new ActivityDto().single(entity);
			}

		} catch (Exception e) {

		}
		return result;
	}

	@Override
	public List<ActivityDto> getActivityListByCreatedUserId(Long userId) {

		List<ActivityDto> result = null;
		List<ActivityDefinition> activityList = null;
		try {
			activityList = repo.findByCreatedUserId(userId);
			if (activityList != null && !activityList.isEmpty()) {
				result = new ArrayList<>();
				for (ActivityDefinition entity : activityList) {
						result.add(new ActivityDto().single(entity));
				}
			}
		} catch (Exception e) {

		}

		return result;
	}

}
