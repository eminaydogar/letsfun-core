package com.easoft.letsfun.service.basic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.TicketDto;
import com.easoft.letsfun.entity.TicketDefinition;
import com.easoft.letsfun.repository.TicketRepository;
import com.easoft.letsfun.service.basic.TicketService;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository repository;

	@Override
	public List<TicketDto> getTicketListByUserId(Long id) {

		List<TicketDto> result = null;

		List<TicketDefinition> resultEntityList = null;

		try {

			resultEntityList = repository.findAllByUserId(id);
			if (resultEntityList != null && !resultEntityList.isEmpty()) {
				result = new ArrayList<>();
				for (TicketDefinition entity : resultEntityList) {
					result.add(new TicketDto(entity));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public List<TicketDto> getTicketListByActivityId(Long id) {
		List<TicketDto> result = null;

		List<TicketDefinition> resultEntityList = null;

		try {

			resultEntityList = repository.findAllByActivityId(id);
			if (resultEntityList != null && !resultEntityList.isEmpty()) {
				result = new ArrayList<>();
				for (TicketDefinition entity : resultEntityList) {
					result.add(new TicketDto(entity));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public List<TicketDto> getTicketList() {
		List<TicketDto> result = null;

		List<TicketDefinition> resultEntityList = null;

		try {

			resultEntityList = repository.findAll();
			if (resultEntityList != null && !resultEntityList.isEmpty()) {
				result = new ArrayList<>();
				for (TicketDefinition entity : resultEntityList) {
					result.add(new TicketDto(entity));
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public TicketDto getTicketWithActivityById(Long id) {
		TicketDto result = null;

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.findById(id).orElse(null);
			if (resultEntity != null) {
				result = new TicketDto().withActivity(resultEntity);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public TicketDto getTicketWithWithUserById(Long id) {
		TicketDto result = null;

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.findById(id).orElse(null);
			if (resultEntity != null) {
				result = new TicketDto().withUser(resultEntity);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public TicketDto getTicketById(Long id) {
		TicketDto result = null;

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.findById(id).orElse(null);
			if (resultEntity != null) {
				result = new TicketDto(resultEntity);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public TicketDto save(TicketDto dto) {
		TicketDto result = null;

		TicketDefinition resultEntity = null;
		;

		try {

			resultEntity = repository.save(dto.copyToEntity(new TicketDefinition()));
			if (resultEntity != null) {
				result = new TicketDto().single(resultEntity);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	@Override
	public TicketDto update(TicketDto dto) {
		TicketDto result = null;

		TicketDefinition resultEntity = null;

		try {

			resultEntity = repository.findById(dto.getId()).orElse(null);
			if (resultEntity != null) {
				resultEntity = repository.saveAndFlush(dto.copyToEntity(resultEntity));
				result = new TicketDto().single(resultEntity);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

}
