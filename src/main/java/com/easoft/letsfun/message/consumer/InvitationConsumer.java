package com.easoft.letsfun.message.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easoft.letsfun.common.dto.DocumentContentDto;
import com.easoft.letsfun.message.MessageConstant;
import com.easoft.letsfun.service.basic.EmailService;

@Component
public class InvitationConsumer {

	@Autowired
	private EmailService mailService;

	@RabbitListener(queues = MessageConstant.INVITATION.invitation_queue)
	public void getMessage(DocumentContentDto message) {
		System.out.println(message.getContent());
		mailService.sendMail("eminaydogarrr@gmail.com", "JOIN PARTY WITH INVITATION !", message.getContent(), true);
	}
}
