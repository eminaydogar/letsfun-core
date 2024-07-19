package com.easoft.letsfun.service.domain;

public interface EmailService {

	public int sendMail(String to, String subject, String text, boolean convertToHtml);

	public int sendMail(String to, String subject, String text, String pathToAttachment, boolean convertToHtml);

	public int sendMailAsync(String to, String subject, String text, boolean convertToHtml);

	public int sendMailAsync(String to, String subject, String text, String pathToAttachment, boolean convertToHtml);
}
