package com.easoft.letsfun.service.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.TicketDto;
import com.easoft.letsfun.entity.TicketDefinition;
import com.easoft.letsfun.repository.TicketRepository;
import com.easoft.letsfun.service.domain.TicketService;

@Transactional
@Service
public class TicketServiceImpl extends BaseDomainService implements TicketService {

	@Autowired
	private TicketRepository repository;

	@Override
	public List<TicketDefinition> getTicketListByUserId(Long id) {

		List<TicketDefinition> resultEntityList = null;

		try {

			resultEntityList = repository.findAllByUserId(id);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntityList;
	}

	@Override
	public List<TicketDefinition> getTicketListByActivityId(Long id) {

		List<TicketDefinition> resultEntityList = null;

		try {

			resultEntityList = repository.findAllByActivityId(id);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntityList;
	}

	@Override
	public List<TicketDefinition> getTicketList() {

		List<TicketDefinition> resultEntityList = null;

		try {

			resultEntityList = repository.findAll();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntityList;
	}

	@Override
	public TicketDefinition getTicketById(Long id) {

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.findById(id).orElse(null);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntity;
	}

	@Override
	public TicketDefinition save(TicketDto dto) {

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.save(dto.copyToEntity(new TicketDefinition()));

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntity;
	}

	@Override
	public TicketDefinition update(TicketDto dto) {

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.findById(dto.getId()).orElse(null);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return resultEntity;
	}

}
