package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;

public interface ActivityJoinRequestService {

	public List<ActivityJoinRequestDto> getActivityJoinRequestListByUserId(Long id);

	public List<ActivityJoinRequestDto> getActivityJoinRequestListByActivityId(Long id);

	public List<ActivityJoinRequestDto> getActivityJoinRequestListByStatus(String status);

	public ActivityJoinRequestDto getActivityJoinRequestWithActivityById(Long id);

	public ActivityJoinRequestDto getActivityJoinRequestWithUserById(Long id);

	public ActivityJoinRequestDto saveEntity(ActivityJoinRequestDto dto);

	public ActivityJoinRequestDto updateEntity(ActivityJoinRequestDto dto);

}
