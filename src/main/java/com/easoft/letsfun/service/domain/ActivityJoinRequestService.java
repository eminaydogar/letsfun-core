package com.easoft.letsfun.service.domain;

import java.util.List;

import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.entity.ActivityJoinRequest;

public interface ActivityJoinRequestService {

	public List<ActivityJoinRequest> getActivityJoinRequestListByUserId(Long id);

	public List<ActivityJoinRequest> getActivityJoinRequestListByActivityId(Long id);

	public List<ActivityJoinRequest> getActivityJoinRequestListByStatus(String status);

	//public ActivityDefinition getActivityJoinRequestWithById(Long id,Class<?>... entities);

	public ActivityJoinRequest saveEntity(ActivityJoinRequestDto dto);

	public ActivityJoinRequest updateEntity(ActivityJoinRequestDto dto);

}
