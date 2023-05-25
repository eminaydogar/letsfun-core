package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.entity.BaseEntity;

public interface ActivityJoinRequestService {

	public List<ActivityJoinRequestDto> getActivityJoinRequestListByUserId(Long id);

	public List<ActivityJoinRequestDto> getActivityJoinRequestListByActivityId(Long id);

	public List<ActivityJoinRequestDto> getActivityJoinRequestListByStatus(String status);

	public ActivityJoinRequestDto getActivityJoinRequestWithById(Long id,Class<?>... entities);

	public ActivityJoinRequestDto saveEntity(ActivityJoinRequestDto dto);

	public ActivityJoinRequestDto updateEntity(ActivityJoinRequestDto dto);

}
