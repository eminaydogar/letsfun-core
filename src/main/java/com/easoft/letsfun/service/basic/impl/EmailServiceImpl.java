package com.easoft.letsfun.service.basic.impl;

import java.io.File;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easoft.letsfun.cache.CacheConstant;
import com.easoft.letsfun.common.dto.LogDto;
import com.easoft.letsfun.service.basic.EmailService;
import com.easoft.letsfun.service.basic.LoggerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private LoggerService loggerService;

	@Override
	public int sendMail(String to, String subject, String text,boolean convertToHtml) {
		


		try {
			/*SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message); */
			
			   MimeMessage msg = emailSender.createMimeMessage();
	           MimeMessageHelper helper = new MimeMessageHelper(msg, false);
	           helper.setTo(to);
	           helper.setSubject(subject);
	           helper.setText(text, convertToHtml);
	           emailSender.send(msg);
			
			writeLog(to);

		} catch (Exception e) {
			writeErrorLog(e);
			log.error(e.getMessage());
			return -1;
		}

		return 1;

	}

	@Override
	public int sendMail(String to, String subject, String text, String pathToAttachment,boolean convertToHtml) {
		MimeMessage message = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text,convertToHtml);
			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment(file.getFilename(), file);
			emailSender.send(message);
			writeLog(to);

		} catch (MessagingException e) {
			writeErrorLog(e);
			log.error(e.getMessage());
			return -1;
		}

		return 1;

	}

	@Async
	@Override
	public int sendMailAsync(String to, String subject, String text,boolean convertToHtml) {
		return sendMail(to, subject, text,convertToHtml);
	}

	@Async
	@Override
	public int sendMailAsync(String to, String subject, String text, String pathToAttachment,boolean convertToHtml) {
		return sendMail(to, subject, text, pathToAttachment,convertToHtml);
	}

	private void writeLog(String to) {
		LogDto dto = new LogDto();
		dto.setCdate(new Date());
		dto.setMethodName("sendMail");
		dto.setProcessType(CacheConstant.SEND_MAIL_VERIFICATION_CODE.Id());
		dto.setResultCode(CacheConstant.SUCCESS.getCode());
		dto.setResultExplanation(CacheConstant.SUCCESS.getValue() +": "+ to);
		loggerService.save(dto);

	}
	
	private void writeErrorLog(Exception e) {
		loggerService.saveError(CacheConstant.SEND_MAIL_VERIFICATION_CODE.Id(), "sendMail", e, null);
	}

}