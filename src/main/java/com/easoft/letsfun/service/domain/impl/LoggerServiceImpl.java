package com.easoft.letsfun.service.domain.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.dto.LogDto;
import com.easoft.letsfun.common.exception.ServiceOperationException;
import com.easoft.letsfun.entity.LogDefinition;
import com.easoft.letsfun.repository.LogRepository;
import com.easoft.letsfun.service.domain.LoggerService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LoggerServiceImpl implements LoggerService {

	@Autowired
	@Setter
	private LogRepository repo;

	@Override
	public void delete(Long id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			log.error("error : loggerService.delete ", e);
		}

	}

	@Override
	public LogDto getById(Long id) {
		LogDefinition logDefinition = null;
		LogDto result = null;
		try {
			logDefinition = repo.findById(id).orElse(null);
			if (logDefinition != null) {
				result = new LogDto(logDefinition);
			}
		} catch (Exception e) {
			log.error("error : loggerService.getById ", e);
		}
		return result;
	}

	@Async
	@Override
	public void save(LogDto dto) {
		try {
			repo.save(dto.copyToEntity(new LogDefinition()));
		} catch (Exception e) {
			log.error("error : loggerService.save ", e);
		}

	}

	@Async
	@Override
	public void update(LogDto dto) {
		LogDefinition entity = null;

		try {
			entity = repo.findById(dto.getId()).orElse(null);
			if (entity != null) {
				repo.saveAndFlush(dto.copyToEntity(entity));
			}

		} catch (Exception e) {
			log.error("error : loggerService.update ", e);
		}

	}

	@Async
	@Override
	public void saveError(Long processType, String methodName, Exception exception, String clientIp) {

		LogDefinition entity = new LogDefinition();
		entity.setProcessType(processType);
		entity.setMethodName(methodName);
		if (exception instanceof ServiceOperationException) {
			ServiceOperationException opExp = (ServiceOperationException) exception;
			entity.setResultCode(opExp.getErrorCode());
			entity.setResultExplanation(opExp.getMessage()+ " ["+opExp.getErrorDetail()+"]");
		} else {
			entity.setResultCode(CacheConstant.SYSTEM_ERROR.getCode());
			entity.setResultExplanation(exception.getMessage());
		}
		
		entity.setClientIp(clientIp);
		entity.setCdate(new Date());

		try {
			repo.save(entity);
		} catch (Exception e) {
			log.error("error : loggerService.save ", e);
		}

	}

	/*
	 * @Async
	 * 
	 * @Override public void writeErrorLog(Long processTyoe, String methodName,
	 * String resultCode, Exception exception) { LogDefinition logDefinition = new
	 * LogDefinition(); logDefinition.setProcessType(processTyoe);
	 * logDefinition.setMethodName(methodName);
	 * logDefinition.setResultCode(resultCode);
	 * logDefinition.setResultExplanation(exception.getMessage());
	 * logDefinition.setCdate(new Date()); repo.save(logDefinition); }
	 * 
	 * @Async
	 * 
	 * @Override public void writeLog(Long processType, String methodName, String
	 * resultCode,String message) {
	 * 
	 * LogDefinition logDefinition = new LogDefinition();
	 * logDefinition.setProcessType(processType);
	 * logDefinition.setResultCode(resultCode);
	 * logDefinition.setMethodName(methodName);
	 * logDefinition.setResultExplanation(message); repo.save(logDefinition);
	 * 
	 * }
	 */

}
