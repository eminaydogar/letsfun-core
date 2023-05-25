package com.easoft.letsfun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.api.request.user.UserUpdateRequest;
import com.easoft.letsfun.common.api.response.UserUpdateResponse;
import com.easoft.letsfun.service.core.AccountFrontService;

@RestController
@RequestMapping("api/mail")
public class GeneralController {
	
	@Autowired
	private AccountFrontService accountFrontService;

	@PostMapping("/update-all-feature")
	public BaseResponse<UserUpdateResponse> updateAllUserFeature(@Valid @RequestBody UserUpdateRequest request) {
		return accountFrontService.updateUserAllProperties(request);
	}

}
