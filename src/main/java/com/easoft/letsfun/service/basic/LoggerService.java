package com.easoft.letsfun.service.basic;

import com.easoft.letsfun.common.dto.LogDto;

public interface LoggerService {

	public void delete(Long id);
	
	public LogDto getById(Long id);
	
	public void save(LogDto dto);
	
	public void saveError(Long processType,String methodName,Exception exception,String clientIp);
	
	public void update(LogDto dto);
	
	//public void writeLog(Long processType, String methodName, String resultCode,String message);

	//public void writeErrorLog(Long processTyoe, String methodName, String resultCode, Exception exception);

}
