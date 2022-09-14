package com.easoft.letsfun.service.basic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.common.dto.ActivityDto;
import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.entity.ActivityJoinRequest;
import com.easoft.letsfun.repository.ActivityJoinRequestRepository;
import com.easoft.letsfun.service.basic.ActivityJoinRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ActivityJoinRequestServiceImpl implements ActivityJoinRequestService {

	@Autowired
	private ActivityJoinRequestRepository repository;

	@Override
	public List<ActivityJoinRequestDto> getActivityJoinRequestListByUserId(Long id) {

		List<ActivityJoinRequestDto> result = null;

		List<ActivityJoinRequest> entityList = null;

		try {
			entityList = repository.findAllByUserId(id);
			if (entityList != null && !entityList.isEmpty()) {
				result = new ArrayList<>();
				for (ActivityJoinRequest entity : entityList) {
					ActivityDto activityDto = new ActivityDto(entity.getActivity());
					ActivityJoinRequestDto ActivityJoinRequestDto = new ActivityJoinRequestDto().single(entity);
					ActivityJoinRequestDto.setActivity(activityDto);
					result.add(ActivityJoinRequestDto);
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
					result.add(new ActivityJoinRequestDto().withUser(entity));
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
					result.add(new ActivityJoinRequestDto(entity));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public ActivityJoinRequestDto getActivityJoinRequestWithActivityById(Long id) {

		ActivityJoinRequestDto result = null;

		ActivityJoinRequest entity = null;

		try {
			entity = repository.findById(id).orElse(null);
			if (entity != null) {
				result = new ActivityJoinRequestDto().withActivity(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public ActivityJoinRequestDto getActivityJoinRequestWithUserById(Long id) {
		ActivityJoinRequestDto result = null;

		ActivityJoinRequest entity = null;

		try {
			entity = repository.findById(id).orElse(null);
			if (entity != null) {
				result = new ActivityJoinRequestDto().withUser(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
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
				result = new ActivityJoinRequestDto().single(entity);
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
				result = new ActivityJoinRequestDto().single(entity);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

}
