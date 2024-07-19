package com.easoft.letsfun.service.domain;

import java.util.List;

import com.easoft.letsfun.common.dto.ActivityDto;
import com.easoft.letsfun.entity.ActivityDefinition;

public interface ActivityService {


	public ActivityDefinition getActivityById(Long id);
	
	public List<ActivityDefinition> getActivityListByCreatedUserId(Long id);
	
	public List<ActivityDefinition> getActiveActivityListByCreatedUserId(Long id);

	public List<ActivityDefinition> getActiveActivityList();

	public ActivityDefinition save(ActivityDto dto);

	public ActivityDefinition update(ActivityDto dto);

}
