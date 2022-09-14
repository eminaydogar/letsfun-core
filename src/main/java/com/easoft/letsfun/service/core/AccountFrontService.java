package com.easoft.letsfun.service.core;

import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.api.request.user.UserActivityInfoRequest;
import com.easoft.letsfun.common.api.request.user.UserDeleteAccountRequest;
import com.easoft.letsfun.common.api.request.user.UserUpdateRequest;
import com.easoft.letsfun.common.api.response.UserActivityInfoResponse;
import com.easoft.letsfun.common.api.response.UserUpdateResponse;

public interface AccountFrontService {
	
	BaseResponse<UserUpdateResponse> updateUserAllProperties(UserUpdateRequest request);
	
	BaseResponse<String> deleteAccount(UserDeleteAccountRequest request);
	
	BaseResponse<UserActivityInfoResponse> getActivityInfo(UserActivityInfoRequest request);

}
