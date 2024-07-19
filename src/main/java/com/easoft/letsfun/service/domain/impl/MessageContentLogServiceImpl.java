package com.easoft.letsfun.service.domain.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.common.dto.MessageContentLogListDto;
import com.easoft.letsfun.entity.MessageContentLogList;
import com.easoft.letsfun.repository.MessageContentLogListRepository;
import com.easoft.letsfun.service.domain.MessageContentLogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class MessageContentLogServiceImpl implements MessageContentLogService {

	@Autowired
	private MessageContentLogListRepository repository;

	@Override
	public MessageContentLogList getMessageContentLogById(Long id) {
		MessageContentLogList entity = null;

		try {
			entity = repository.findById(id).orElse(null);

		} catch (Exception e) {
			log.error("getMessageContentLogById e ", e);
		}

		return entity;
	}

	@Override
	public MessageContentLogList getListByMessageTypeAndUserIdAndContent(Long messageType, Long userid,String content) {
		MessageContentLogList entity = null;

		try {
			entity = repository.findByMessageTypeAndRelatedUserAndMessageContentAndStatus(messageType, userid,content, "Y");

		} catch (Exception e) {
			log.error("getMessageContentLogByMessageTypeAndUserId e ", e);
		}

		return entity;
	}

	@Override
	public MessageContentLogList save(MessageContentLogListDto dto) {

		MessageContentLogList entityResult = null;
		try {

			dto.setCdate(new Date());
			dto.setStatus("Y");
			entityResult = repository.save(dto.copyToEntity(new MessageContentLogList()));

		} catch (Exception e) {
			log.error("save e ", e);
		}

		return entityResult;
	}

	@Override
	public MessageContentLogList update(MessageContentLogListDto dto) {

		MessageContentLogList entityResult = null;
		try {
			entityResult = repository.findById(dto.getId()).orElse(null);

			if (entityResult != null) {
				entityResult = repository.saveAndFlush(dto.copyToEntity(entityResult));

			}

		} catch (Exception e) {
			log.error("update e ", e);
		}

		return entityResult;
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

}
