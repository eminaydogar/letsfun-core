package com.easoft.letsfun.cache;

import com.easoft.letsfun.entity.resultset.ObjectValue;

public enum CacheConstant {

	SUCCESS("S0"),
	SYSTEM_ERROR("E0"),
	SERVIS_ERROR("E1"),
	VERIFICATION_MAIL_SENDING("VERIFICATION_MAIL_SENDING"),
	SEND_MAIL_VERIFICATION_CODE("SEND_MAIL_VERIFICATION_CODE"),
	PROCESS_SERVIS_ERROR("SERVIS_ERROR"),
	VERIFICATION_CODE_LENGTH("VERIFICATION_CODE_LENGTH"),
	MAIL_CHANNEL("MAIL_CHANNEL"),
	VERIFICATION_MAIL_CONTENT("VERIFICATION_MAIL_CONTENT"),
    INVITATION_TEMPLATE("INVITATION_TEMPLATE"),
	REJECT_USER_DISABLED("REJECT_USER_DISABLED");
	

	private final String objectCode;

	CacheConstant(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getCode() {
		return objectCode;
	}
	
	public String getValue() {
		ObjectValue value = CacheManager.getItemByObjectCode(objectCode);

		if (value != null) {
			return value.getObjectName();
		}
		return null;
	}
	
	public Long Id() {
		ObjectValue value = CacheManager.getItemByObjectCode(objectCode);

		if (value != null) {
			return value.getId();
		}
		return null;
	}

}
