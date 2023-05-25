package com.easoft.letsfun.service.basic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.MessageContentLogListDto;
import com.easoft.letsfun.entity.MessageContentLogList;
import com.easoft.letsfun.repository.MessageContentLogListRepository;
import com.easoft.letsfun.service.basic.MessageContentLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class MessageContentLogServiceImpl implements MessageContentLogService {

	@Autowired
	private MessageContentLogListRepository repository;

	@Override
	public MessageContentLogListDto getMessageContentLogById(Long id) {
		MessageContentLogListDto result = null;

		try {
			MessageContentLogList entity = repository.findById(id).orElse(null);
			if (entity != null) {
				result = new MessageContentLogListDto(entity);
			}

		} catch (Exception e) {
			log.error("getMessageContentLogById e ", e);
		}

		return result;
	}

	@Override
	public List<MessageContentLogListDto> getMessageContentLogListByMessageTypeAndUserId(Long messageType,
			Long userid) {
		List<MessageContentLogListDto> result = null;

		try {
			List<MessageContentLogList> entityList = repository.findByMessageTypeAndRelatedUserAndStatus(messageType,
					userid, "Y");
			if (entityList != null && !entityList.isEmpty()) {
				result = new ArrayList<>();
				for (MessageContentLogList entity : entityList) {
					result.add(new MessageContentLogListDto(entity));
				}
			}

		} catch (Exception e) {
			log.error("getMessageContentLogByMessageTypeAndUserId e ", e);
		}

		return result;
	}

	@Override
	public MessageContentLogListDto save(MessageContentLogListDto dto) {

		MessageContentLogListDto result = null;
		MessageContentLogList entityResult = null;
		try {

			dto.setCdate(new Date());
			dto.setStatus("Y");
			entityResult = repository.save(dto.copyToEntity(new MessageContentLogList()));
			if (entityResult != null) {
				result = new MessageContentLogListDto(entityResult);
			}

		} catch (Exception e) {
			log.error("save e ", e);
		}

		return result;
	}

	@Override
	public MessageContentLogListDto update(MessageContentLogListDto dto) {
		MessageContentLogListDto result = null;
		MessageContentLogList entityResult = null;
		try {
			entityResult = repository.findById(dto.getId()).orElse(null);

			if (entityResult != null) {
				entityResult = repository.saveAndFlush(dto.copyToEntity(entityResult));
				result = new MessageContentLogListDto(entityResult);
			}

		} catch (Exception e) {
			log.error("update e ", e);
		}

		return result;
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

}
