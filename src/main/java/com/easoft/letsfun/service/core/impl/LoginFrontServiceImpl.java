package com.easoft.letsfun.service.core.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.Constants.RECORD_STATUS;
import com.easoft.letsfun.common.Constants.USER_STATUS;
import com.easoft.letsfun.common.api.BaseResponse;
import com.easoft.letsfun.common.api.bean.UserBean;
import com.easoft.letsfun.common.api.request.login.LoginControlRequest;
import com.easoft.letsfun.common.api.request.login.RegisterRequest;
import com.easoft.letsfun.common.api.request.login.RegisterVerifyRequest;
import com.easoft.letsfun.common.api.response.LoginControlResponse;
import com.easoft.letsfun.common.api.response.RegisterResponse;
import com.easoft.letsfun.common.api.response.RegisterVerifyResponse;
import com.easoft.letsfun.common.dto.MessageContentLogListDto;
import com.easoft.letsfun.common.dto.UserDto;
import com.easoft.letsfun.common.exception.EntityNotFoundException;
import com.easoft.letsfun.common.exception.ServiceOperationException;
import com.easoft.letsfun.entity.DocumentContentDefinition;
import com.easoft.letsfun.entity.MessageContentLogList;
import com.easoft.letsfun.entity.UserDefinition;
import com.easoft.letsfun.security.PasswordCrypter;
import com.easoft.letsfun.security.RoleType;
import com.easoft.letsfun.service.core.LoginFrontService;
import com.easoft.letsfun.service.domain.DocumentContentService;
import com.easoft.letsfun.service.domain.EmailService;
import com.easoft.letsfun.service.domain.LoggerService;
import com.easoft.letsfun.service.domain.MessageContentLogService;
import com.easoft.letsfun.service.domain.UserService;
import com.easoft.letsfun.util.CacheUtil;
import com.easoft.letsfun.util.MailUtil;
import com.easoft.letsfun.util.ObjectUtilty;
import com.easoft.letsfun.util.SecureUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginFrontServiceImpl implements LoginFrontService {

	@Autowired
	private UserService userService;

	@Autowired
	private LoggerService loggerService;

	@Autowired
	private EmailService mailService;

	@Autowired
	private MessageContentLogService messageContentLogService;

	@Autowired
	private DocumentContentService documentService;

	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	@Override
	public BaseResponse<LoginControlResponse> loginControl(LoginControlRequest request) {

		UserDefinition user = null;
		LoginControlResponse loginControlResponse = null;
		BaseResponse<LoginControlResponse> response = new BaseResponse<>();

		try {
			user = userService.getUserByUsername(request.getUsername());
			if (user != null && PasswordCrypter.instance().matches(request.getPassword(), user.getPassword())) {

				UserDto userDto = new UserDto(user);
				userDto.setLastLoginDate(new Date());

				user = userService.update(userDto);

				loginControlResponse = new LoginControlResponse();

				UserBean userBean = new UserBean();

				userBean.setUsername(user.getUsername());
				userBean.setAddress(CacheUtil.findAddresses(user.getAddress()));
				userBean.setEmail(user.getEmail());
				userBean.setImage(user.getImage());
				userBean.setPhoneNumber(user.getPhoneNumber());
				userBean.setVertify(user.getVertify());
				userBean.setStatus(user.getStatus());

				loginControlResponse.setUser(userBean);
				response.setSuccessResponse(loginControlResponse);

			} else {
				throw new EntityNotFoundException("Username or Password invalid");
			}
		} catch (Exception e) {
			loggerService.saveError(CacheConstant.PROCESS_SERVIS_ERROR.Id(), "loginControl", e, request.getClientIp());
			log.error("loginControl e: ", e);
			response.setFaildResponse(e);
		}

		return response;
	}

	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	@Override
	public BaseResponse<RegisterResponse> register(RegisterRequest request) {

		UserDto user = new UserDto();
		RegisterResponse registerResponse = null;
		BaseResponse<RegisterResponse> response = new BaseResponse<>();

		try {

			boolean isExistResult = userService.existUserControlByUsernameOrEmail(request.getUsername(),
					request.getEmail());

			if (isExistResult) {
				throw new ServiceOperationException("Username or email allready exists");
			}

			user.setAddress(request.getAddress());
			user.setCdate(new Date());
			user.setEmail(request.getEmail());
			user.setImage(request.getImage());
			user.setIsBlackList(RECORD_STATUS.INACTIVE);
			user.setPassword(PasswordCrypter.instance().encode(request.getPassword()));
			user.setPhoneNumber(request.getPhoneNumber());
			user.setUsername(request.getUsername());
			user.setVertify(RECORD_STATUS.INACTIVE);
			user.setRoles(RoleType.USER.getUserRole());

			UserDefinition saveUser = userService.save(user);

			if (saveUser != null) {

				MessageContentLogList messageLog = generateVertifyCodeAndLog(user);

				DocumentContentDefinition document = documentService
						.getDocumentContentByType(CacheConstant.VERIFICATION_MAIL_CONTENT.Id());
				if (document != null) {
					String mailContent = document.getContent();
					Map<String, String> contentMap = new HashMap<>();
					contentMap.put("%USERNAME%", user.getUsername());
					contentMap.put("%VERIFICATION_CODE%", messageLog.getMessageContent());
					mailContent = MailUtil.editContent(contentMap, mailContent);
					mailService.sendMailAsync(user.getEmail(), "Verification Mail", mailContent, true);
				}

				registerResponse = new RegisterResponse();
				UserBean userBean = new UserBean();
				userBean.setUsername(user.getUsername());
				userBean.setAddress(CacheUtil.findAddresses(user.getAddress()));
				userBean.setEmail(user.getEmail());
				userBean.setImage(user.getImage());
				userBean.setPhoneNumber(user.getPhoneNumber());
				userBean.setVertify(user.getVertify());

				registerResponse.setUser(userBean);
				response.setSuccessResponse(registerResponse);
			}

		} catch (Exception e) {
			loggerService.saveError(CacheConstant.PROCESS_SERVIS_ERROR.Id(), "register", e, request.getClientIp());
			log.error("register e: ", e);
			response.setFaildResponse(e);
		}

		return response;
	}

	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	@Override
	public BaseResponse<RegisterVerifyResponse> registerVerify(RegisterVerifyRequest request) {

		BaseResponse<RegisterVerifyResponse> response = new BaseResponse<>();
		RegisterVerifyResponse verifyResponse = null;
		try {
			UserDefinition user = userService.getUserByUsername(request.getUsername());
			if (user == null || USER_STATUS.ACTIVE.equalsIgnoreCase(user.getVertify())) {
				throw new ServiceOperationException("User has already verify");
			}
			MessageContentLogList messageContentLog = messageContentLogService.getListByMessageTypeAndUserIdAndContent(
					CacheConstant.VERIFICATION_MAIL_SENDING.Id(), user.getId(), request.getVerifyCode());

			if (messageContentLog != null) {

				if (messageContentLog.getEdate() != null && messageContentLog.getEdate().after(new Date())) {

					UserDto userDto = new UserDto(user);
					user.setVertify(USER_STATUS.ACTIVE);
					user = userService.update(userDto);

					MessageContentLogListDto messageLogDto = new MessageContentLogListDto(messageContentLog);

					messageLogDto.setStatus(RECORD_STATUS.INACTIVE);
					messageContentLog = messageContentLogService.update(messageLogDto);

					verifyResponse = new RegisterVerifyResponse();
					verifyResponse.setProcessDate(new Date());
					verifyResponse.setSuccess(true);
					verifyResponse.setVerifyUserName(user.getUsername());

				} else {
					throw new ServiceOperationException("Your verification code has expired");
				}

			} else {
				throw new ServiceOperationException("No verification code found for user");
			}

			response.setSuccessResponse(verifyResponse);

		} catch (Exception e) {
			log.error("registerVerify e ", e);
			loggerService.saveError(CacheConstant.PROCESS_SERVIS_ERROR.Id(), "registerVerify", e,
					request.getClientIp());
			response.setFaildResponse(e);
		}

		return response;

	}

	private MessageContentLogList generateVertifyCodeAndLog(UserDto user) {
		String vertifyCode = SecureUtility.getInstance()
				.generateVertifyCode(Integer.valueOf(CacheConstant.VERIFICATION_CODE_LENGTH.getValue()));
		MessageContentLogListDto dto = new MessageContentLogListDto();
		dto.setChannelType(CacheConstant.MAIL_CHANNEL.Id());
		dto.setEdate(ObjectUtilty.createNextDate(ObjectUtilty.MINUTE, 10));
		dto.setMessageContent(vertifyCode);
		dto.setMessageType(CacheConstant.VERIFICATION_MAIL_SENDING.Id());
		dto.setRelatedUser(user);

		MessageContentLogList log = messageContentLogService.save(dto);
		return log;
	}

}
