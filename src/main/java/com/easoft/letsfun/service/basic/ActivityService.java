package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.ActivityDto;

public interface ActivityService {


	public ActivityDto getActivityById(Long id,Class<?>... entites);
	
	public List<ActivityDto> getActivityListByCreatedUserId(Long id);

	public List<ActivityDto> getActiveActivityList();

	public ActivityDto save(ActivityDto dto);

	public ActivityDto update(ActivityDto dto);

}
