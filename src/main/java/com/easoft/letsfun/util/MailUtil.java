package com.easoft.letsfun.util;

import java.util.Map;

public class MailUtil {

	public static String editContent(Map<String, String> values, String mailContent) {
		for (String key : values.keySet()) {
			mailContent = mailContent.replaceAll(key, values.get(key));
		}
		return mailContent;
	}
	

}
