package com.easoft.letsfun.service.domain.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.entity.ActivityJoinRequest;
import com.easoft.letsfun.repository.ActivityJoinRequestRepository;
import com.easoft.letsfun.service.domain.ActivityJoinRequestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class ActivityJoinRequestServiceImpl extends BaseDomainService implements ActivityJoinRequestService {

	@Autowired
	private ActivityJoinRequestRepository repository;

	@Override
	public List<ActivityJoinRequest> getActivityJoinRequestListByUserId(Long id) {

		List<ActivityJoinRequest> entityList = null;

		try {
			entityList = repository.findAllByUserId(id);

		} catch (Exception e) {
			log.error("getActivityJoinRequestListByUserId", e);
			System.out.println(e.getMessage());
		}

		return entityList;
	}

	@Override
	public List<ActivityJoinRequest> getActivityJoinRequestListByActivityId(Long id) {

		List<ActivityJoinRequest> entityList = null;

		try {
			entityList = repository.findAllByActivityId(id);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return entityList;
	}

	@Override
	public List<ActivityJoinRequest> getActivityJoinRequestListByStatus(String status) {

		List<ActivityJoinRequest> entityList = null;

		try {
			entityList = repository.findAllByRequestStatus(status);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return entityList;
	}


	@Override
	public ActivityJoinRequest saveEntity(ActivityJoinRequestDto dto) {

		ActivityJoinRequest entity = null;

		try {
			entity = repository.save(dto.copyToEntity(new ActivityJoinRequest()));

		} catch (Exception e) {
			// TODO: handle exception
		}

		return entity;
	}


	@Override
	public ActivityJoinRequest updateEntity(ActivityJoinRequestDto dto) {

		ActivityJoinRequest entity = null;

		try {
			entity = repository.findById(dto.getId()).orElse(null);
			if (entity != null) {
				entity = repository.saveAndFlush(entity);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return entity;
	}

}
