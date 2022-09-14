package com.easoft.letsfun.service.basic;

import java.util.List;

import com.easoft.letsfun.common.dto.MessageContentLogListDto;

public interface MessageContentLogService {

	public MessageContentLogListDto getMessageContentLogById(Long id);

	public List<MessageContentLogListDto> getMessageContentLogListByMessageTypeAndUserId(Long messageType, Long userid);

	public MessageContentLogListDto save(MessageContentLogListDto dto);

	public MessageContentLogListDto update(MessageContentLogListDto dto);
	
	public void deleteById(Long id);

}
