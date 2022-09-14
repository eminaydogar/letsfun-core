package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.ActivityDto;

public interface ActivityService {

	public ActivityDto getActivityWithUserById(Long id);

	public ActivityDto getActivityById(Long id);
	
	public List<ActivityDto> getActivityListByCreatedUserId(Long id);

	public List<ActivityDto> getActiveActivityList();

	public ActivityDto save(ActivityDto dto);

	public ActivityDto update(ActivityDto dto);

}
