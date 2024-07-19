package com.easoft.letsfun.service.domain;

import java.util.List;

import com.easoft.letsfun.common.dto.MessageContentLogListDto;
import com.easoft.letsfun.entity.MessageContentLogList;

public interface MessageContentLogService {

	public MessageContentLogList getMessageContentLogById(Long id);

	public MessageContentLogList getListByMessageTypeAndUserIdAndContent(Long messageType, Long userid,String content);

	public MessageContentLogList save(MessageContentLogListDto dto);

	public MessageContentLogList update(MessageContentLogListDto dto);
	
	public void deleteById(Long id);

}
