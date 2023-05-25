package com.easoft.letsfun.service.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.api.bean.ActivityBean;
import com.easoft.letsfun.common.api.bean.ActivityDetailBean;
import com.easoft.letsfun.common.api.bean.ActivityJoinRequestBean;
import com.easoft.letsfun.common.api.bean.UserBean;
import com.easoft.letsfun.common.api.request.user.UserActivityInfoRequest;
import com.easoft.letsfun.common.api.request.user.UserDeleteAccountRequest;
import com.easoft.letsfun.common.api.request.user.UserUpdateRequest;
import com.easoft.letsfun.common.api.response.UserActivityInfoResponse;
import com.easoft.letsfun.common.api.response.UserUpdateResponse;
import com.easoft.letsfun.common.aspect.CheckAuth;
import com.easoft.letsfun.common.dto.ActivityDto;
import com.easoft.letsfun.common.dto.ActivityJoinRequestDto;
import com.easoft.letsfun.common.dto.TicketDto;
import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.common.exception.EntityNotFoundException;
import com.easoft.letsfun.common.exception.ServiceOperationException;
import com.easoft.letsfun.security.PasswordCrypter;
import com.easoft.letsfun.service.BaseService;
import com.easoft.letsfun.service.basic.ActivityJoinRequestService;
import com.easoft.letsfun.service.basic.ActivityService;
import com.easoft.letsfun.service.basic.LoggerService;
import com.easoft.letsfun.service.basic.TicketService;
import com.easoft.letsfun.service.basic.UserService;
import com.easoft.letsfun.service.core.AccountFrontService;
import com.easoft.letsfun.util.CacheUtil;
import com.easoft.letsfun.util.ObjectUtilty;
import com.easoft.letsfun.util.SecureUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountFrontServiceImpl extends BaseService implements AccountFrontService {

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityJoinRequestService activityJoinRequestService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private LoggerService loggerService;

	@CheckAuth
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	@Override
	public BaseResponse<UserUpdateResponse> updateUserAllProperties(UserUpdateRequest request) {
		BaseResponse<UserUpdateResponse> response = new BaseResponse<>();
		UserUpdateResponse userResponse = null;
		boolean passwordChanged = false;

		try {

			UserDto user = userService.getSimpleUserById(request.getUserId());
			if (user != null) {
				if (isVerifiedUser(user)) {
					user.setAddress(request.getAddress());
					user.setEmail(request.getEmail());
					user.setPhoneNumber(request.getPhoneNumber());
					user.setImage(request.getImage());
					if (request.isPasswordChange() && request.getNewPassword() != null
							&& request.getPassword() != null) {
						if (PasswordCrypter.instance().matches(request.getPassword(), user.getPassword())) {
							user.setPassword(PasswordCrypter.instance().encode(request.getNewPassword()));
							passwordChanged = true;
						}
					}

					user = userService.update(user);

					userResponse = new UserUpdateResponse();
					UserBean userBean = new UserBean();

					userBean.setAddress(CacheUtil.findAddresses(user.getAddress()));
					userBean.setEmail(user.getEmail());
					userBean.setImage(user.getImage());
					userBean.setPhoneNumber(user.getPhoneNumber());
					userBean.setUsername(user.getUsername());
					userBean.setVertify(user.getVertify());

					userResponse.setPasswordUpdated(passwordChanged);

					userResponse.setUser(userBean);
					response.setSuccessResponse(userResponse);

				} else {
					throw new ServiceOperationException("The operation was rejected because the user was not verified");
				}

			} else
				throw new EntityNotFoundException("Failed to fetch user information");

		} catch (Exception e) {
			log.error("updateUserAllProperties e ", e);
			loggerService.saveError(CacheConstant.PROCESS_SERVIS_ERROR.Id(), "updateUserAllProperties", e,
					request.getClientIp());
			response.setFaildResponse(e);
		}

		return response;
	}

	@CheckAuth
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	@Override
	public BaseResponse<String> deleteAccount(UserDeleteAccountRequest request) {
		BaseResponse<String> response = new BaseResponse<>();
		String result = null;

		try {
			UserDto user = userService.getSimpleUserById(request.getUserId());
			if (user != null && "Y".equalsIgnoreCase(user.getStatus())) {

				List<ActivityDto> activityList = activityService.getActivityListByCreatedUserId(request.getUserId());
				if (activityList != null && !activityList.isEmpty()) {
					for (ActivityDto activity : activityList) {
						if ("Y".equals(activity.getStatus())) {
							activity.setStatus("N");
							activity.setEdate(new Date());
							activityService.update(activity);
						}

					}
				}

				List<ActivityJoinRequestDto> activityJoinRequestList = activityJoinRequestService
						.getActivityJoinRequestListByUserId(request.getUserId());

				if (activityJoinRequestList != null && !activityJoinRequestList.isEmpty()) {
					for (ActivityJoinRequestDto activityRequest : activityJoinRequestList) {
						if ("Y".equals(activityRequest.getRequestStatus())) {
							activityRequest.setRequestStatus("N");
							activityRequest.setRejectReason(CacheConstant.REJECT_USER_DISABLED.getValue());
							activityJoinRequestService.updateEntity(activityRequest);
						}

					}
				}

				List<TicketDto> ticketList = ticketService.getTicketListByUserId(request.getUserId());

				if (ticketList != null && !ticketList.isEmpty()) {
					for (TicketDto ticket : ticketList) {
						if ("Y".equals(ticket.getStatus())) {
							ticket.setStatus("N");
							ticketService.update(ticket);
						}
					}
				}

				user.setStatus("N");
				userService.update(user);
				result = "User disabled successfully";
				response.setResponse(result);

			} else {
				throw new ServiceOperationException("User was already disable", null,
						"username : " + user.getUsername());
			}
		} catch (Exception e) {
			log.error("deleteAccount e ", e);
			loggerService.saveError(CacheConstant.PROCESS_SERVIS_ERROR.Id(), "deleteAccount", e, request.getClientIp());
			response.setFaildResponse(e);
		}

		return response;
	}

	@CheckAuth
	@Override
	public BaseResponse<UserActivityInfoResponse> getActivityInfo(UserActivityInfoRequest request) {
		BaseResponse<UserActivityInfoResponse> response = new BaseResponse<>();
		UserActivityInfoResponse userActivityInfoResponse = null;

		try {

			UserDto user = userService.getSimpleUserById(request.getUserId());
			if (user != null) {
				userActivityInfoResponse = new UserActivityInfoResponse();
				List<ActivityBean> activityBeanList = null;
				List<ActivityJoinRequestBean> activityJoinRequestBeanList = null;

				List<ActivityDto> userCreatedActivityList = activityService.getActivityListByCreatedUserId(request.getUserId());
				
				if (!ObjectUtilty.isEmpty(userCreatedActivityList)) {
					
					activityBeanList = new ArrayList<>();
					
					for (ActivityDto createdActivity : userCreatedActivityList) {
						
						ActivityBean activityBean = new ActivityBean();
						activityBean.setAdvertImage(createdActivity.getAdvertImage());
						activityBean.setCapacity(createdActivity.getCapacity());
						activityBean.setCdate(createdActivity.getCdate());
						activityBean.setContent(createdActivity.getContent());
						activityBean.setEdate(createdActivity.getEdate());
						activityBean.setId(createdActivity.getId());
						activityBean.setStatus(createdActivity.getStatus());
						activityBean.setTitle(createdActivity.getTitle());
						activityBean.setType(CacheUtil.findObjectValue(createdActivity.getTypeId().toString()));

						if (createdActivity.getDetails() != null) {
							
							ActivityDetailBean detailBean = new ActivityDetailBean();
							detailBean.setTicketContentId(createdActivity.getDetails().getTicketContentId());
							detailBean.setId(createdActivity.getDetails().getId());
							detailBean.setParticipantsNumber(createdActivity.getDetails().getParticipantsNumber());
							detailBean.setAddress(CacheUtil.findAddresses(createdActivity.getDetails().getAddress()));
							detailBean.setAddressDetail(createdActivity.getDetails().getAddressDetail());
							detailBean.setFeatures(
									CacheUtil.findObjectValueList(createdActivity.getDetails().getFeatureValues()));
							detailBean.setCdate(createdActivity.getDetails().getCdate());

							activityBean.setDetails(detailBean);
						}

						activityBeanList.add(activityBean);

					}
				}

				List<ActivityJoinRequestDto> activityJoinRequestList = activityJoinRequestService.getActivityJoinRequestListByUserId(request.getUserId());

				if (activityJoinRequestList != null && !activityJoinRequestList.isEmpty()) {
					
					activityJoinRequestBeanList = new ArrayList<>();
					
					for (ActivityJoinRequestDto activityJoinRequest : activityJoinRequestList) {
						
						ActivityJoinRequestBean activityJoinRequestBean = new ActivityJoinRequestBean();
						activityJoinRequestBean.setCdate(activityJoinRequest.getCdate());
						activityJoinRequestBean.setId(activityJoinRequest.getId());
						activityJoinRequestBean.setRequestStatus(activityJoinRequest.getRequestStatus());
						activityJoinRequestBean.setRejectReason(activityJoinRequest.getRejectReason());
						
						if (activityJoinRequest.getActivity() != null) {
							
							ActivityBean activityBean = new ActivityBean();
							activityBean.setAdvertImage(activityJoinRequest.getActivity().getAdvertImage());
							activityBean.setCapacity(activityJoinRequest.getActivity().getCapacity());
							activityBean.setCdate(activityJoinRequest.getActivity().getCdate());
							activityBean.setContent(activityJoinRequest.getActivity().getContent());
							activityBean.setEdate(activityJoinRequest.getActivity().getEdate());
							activityBean.setId(activityJoinRequest.getActivity().getId());
							activityBean.setStatus(activityJoinRequest.getActivity().getStatus());
							activityBean.setTitle(activityJoinRequest.getActivity().getTitle());
							activityBean.setType(CacheUtil
									.findObjectValue(activityJoinRequest.getActivity().getTypeId().toString()));
							
							if (activityJoinRequest.getActivity().getCreatedUser() != null) {
								
								UserBean userBean = new UserBean();
								userBean.setUsername(activityJoinRequest.getActivity().getCreatedUser().getUsername());
								activityBean.setCreatedUser(userBean);
								
							}
							
							if (activityJoinRequest.getActivity().getDetails() != null) {
								
								ActivityDetailBean detailBean = new ActivityDetailBean();
								detailBean.setCdate(activityJoinRequest.getActivity().getDetails().getCdate());
								detailBean.setId(activityJoinRequest.getActivity().getDetails().getId());
								detailBean.setParticipantsNumber(
										activityJoinRequest.getActivity().getDetails().getParticipantsNumber());
								detailBean.setAddress(CacheUtil
										.findAddresses(activityJoinRequest.getActivity().getDetails().getAddress()));
								detailBean.setAddressDetail(SecureUtility.getInstance().hideAddress(
										activityJoinRequest.getActivity().getDetails().getAddressDetail(), 3, 2));
								detailBean.setFeatures(CacheUtil.findObjectValueList(
										activityJoinRequest.getActivity().getDetails().getFeatureValues()));
								activityBean.setDetails(detailBean);
								
							}
							
							activityJoinRequestBean.setActivity(activityBean);
							
						}
						
						activityJoinRequestBeanList.add(activityJoinRequestBean);
					}

				}

				userActivityInfoResponse.setCreatedActivities(activityBeanList);
				userActivityInfoResponse.setJoinRequestActivities(activityJoinRequestBeanList);
				userActivityInfoResponse.setUserId(user.getId());

				response.setSuccessResponse(userActivityInfoResponse);

			}

		} catch (Exception e) {
			response.setFaildResponse(e);
		}

		return response;
	}

}
