package com.easoft.letsfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.common.dto.DocumentContentDto;
import com.easoft.letsfun.entity.ActivityDefinition;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.message.MessageConstant;
import com.easoft.letsfun.message.Producer;
import com.easoft.letsfun.message.publisher.InvitationPublisher;
import com.easoft.letsfun.service.basic.ActivityJoinRequestService;
import com.easoft.letsfun.service.basic.DocumentContentService;
import com.easoft.letsfun.service.basic.EmailService;

@RestController
@RequestMapping(value = "api/test")
public class TestController {

	@Autowired
	private DocumentContentService documentService;

	@Autowired
	private InvitationPublisher publisher;

	@GetMapping(value = "/invitation-mail-test")
	public BaseResponse<ActivityJoinRequestDto> test1() {
		DocumentContentDto dto = documentService.getDocumentContentByType(CacheConstant.INVITATION_TEMPLATE.Id());
		BaseResponse<ActivityJoinRequestDto> response = new BaseResponse<>();
		publisher.sendMessage(dto);
		return response;
	}

}
