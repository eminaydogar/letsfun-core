package com.easoft.letsfun.service.basic.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.common.aspect.LazyInvoke;
import com.easoft.letsfun.common.dto.ActivityDto;
import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.ActivityJoinRequest;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.repository.ActivityJoinRequestRepository;
import com.easoft.letsfun.service.basic.ActivityJoinRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ActivityJoinRequestServiceImpl extends BaseDomainService implements ActivityJoinRequestService {

	@Autowired
	private ActivityJoinRequestRepository repository;

	@Override
	public List<ActivityJoinRequestDto> getActivityJoinRequestListByUserId(Long id) {

		List<ActivityJoinRequestDto> result = null;

		try {
			List<ActivityJoinRequest> entityList = repository.findAllByUserId(id);
			
			if (entityList != null && !entityList.isEmpty()) {
				
				result = new ArrayList<>();
				
				for (ActivityJoinRequest entity : entityList) {
					
					ActivityJoinRequestDto activityJoinRequestDto = new ActivityJoinRequestDto(entity);
					
					activityJoinRequestDto.setActivity(new ActivityDto(entity.getActivity()));  // or lazyInvoke(entity, ActivityJoinRequestDto, ActivityDefinition.class);
					
					result.add(activityJoinRequestDto);
				}
			}
		} catch (Exception e) {
			log.error("getActivityJoinRequestListByUserId", e);
			System.out.println(e.getMessage());
		}

		return result;
	}

	@Override
	public List<ActivityJoinRequestDto> getActivityJoinRequestListByActivityId(Long id) {

		List<ActivityJoinRequestDto> result = null;

		List<ActivityJoinRequest> entityList = null;

		try {
			entityList = repository.findAllByActivityId(id);
			if (entityList != null && !entityList.isEmpty()) {
				
				result = new ArrayList<>();
				
				for (ActivityJoinRequest entity : entityList) {
					
					ActivityJoinRequestDto activityJoinRequestDto = new ActivityJoinRequestDto(entity);
					
					activityJoinRequestDto.setUser(new UserDto(entity.getUser())); //or lazyInvoke(entity, dto, UserDefinition.class);
					
					result.add(activityJoinRequestDto);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public List<ActivityJoinRequestDto> getActivityJoinRequestListByStatus(String status) {

		List<ActivityJoinRequestDto> result = null;

		List<ActivityJoinRequest> entityList = null;

		try {
			entityList = repository.findAllByRequestStatus(status);
			
			if (entityList != null && !entityList.isEmpty()) {
				
				result = new ArrayList<>();
				
				for (ActivityJoinRequest entity : entityList) {
					
					ActivityJoinRequestDto activityJoinRequestDto = new ActivityJoinRequestDto(entity);
					
					activityJoinRequestDto.setActivity(new ActivityDto(entity.getActivity()));
					activityJoinRequestDto.setUser(new UserDto(entity.getUser()));
					//or lazyInvoke(entity, activityJoinRequestDto, UserDefinition.class, ActivityDefinition.class); 
					
					result.add(activityJoinRequestDto);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public ActivityJoinRequestDto getActivityJoinRequestWithById(Long id, Class<?>... entities) {

		ActivityJoinRequestDto result = null;

		ActivityJoinRequest entity = null;

		try {
			entity = repository.findById(id).orElse(null);
			if (entity != null) {

				result = new ActivityJoinRequestDto(entity);
				lazyInvoke(entity, result, entities);
			}

		} catch (Exception e) {
			System.out.println("HATA " + e.getMessage());
		}

		return result;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public ActivityJoinRequestDto saveEntity(ActivityJoinRequestDto dto) {

		ActivityJoinRequestDto result = null;

		ActivityJoinRequest entity = null;

		try {
			entity = repository.save(dto.copyToEntity(new ActivityJoinRequest()));
			if (entity != null) {
				result = new ActivityJoinRequestDto(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Transactional(rollbackOn = Exception.class)
	@Override
	public ActivityJoinRequestDto updateEntity(ActivityJoinRequestDto dto) {

		ActivityJoinRequestDto result = null;

		ActivityJoinRequest entity = null;

		try {
			entity = repository.findById(dto.getId()).orElse(null);
			if (entity != null) {
				entity = repository.saveAndFlush(entity);
				result = new ActivityJoinRequestDto(entity);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

}
