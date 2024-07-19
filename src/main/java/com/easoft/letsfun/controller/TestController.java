package com.easoft.letsfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.entity.DocumentContentDefinition;
import com.easoft.letsfun.entity.RoleDefinition;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.message.publisher.InvitationPublisher;
import com.easoft.letsfun.service.core.impl.LoginFrontServiceImpl;
import com.easoft.letsfun.service.domain.DocumentContentService;
import com.easoft.letsfun.service.domain.UserService;

@RestController
@RequestMapping(value = "api/test")
public class TestController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private DocumentContentService documentService;

	@Autowired
	private InvitationPublisher publisher;
	
	
	@Autowired
	private LoginFrontServiceImpl loginFrontService;

	@GetMapping(value = "/invitation-mail-test")
	public BaseResponse<ActivityJoinRequestDto> test1() {
		DocumentContentDefinition dto = documentService.getDocumentContentByType(CacheConstant.INVITATION_TEMPLATE.Id());
		BaseResponse<ActivityJoinRequestDto> response = new BaseResponse<>();
		publisher.sendMessage(dto);
		return response;
	}
	
	
	@GetMapping(value = "/id-getir")
	public BaseResponse<?> test2() {
	UserDefinition user =	userService.getUserByUsername("emin123");
		
	RoleDefinition rd = user.getRoles().iterator().next();
		for(int i =0; i<100000;i++) {
			
		}
		BaseResponse<RoleDefinition> response = new BaseResponse<>();
		response.setSuccessResponse(rd);
	
	
		return response;
	}


}
