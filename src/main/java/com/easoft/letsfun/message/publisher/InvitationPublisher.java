package com.easoft.letsfun.message.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easoft.letsfun.message.MessageConstant;
import com.easoft.letsfun.message.Producer;

@Service
public class InvitationPublisher implements Publisher {

	@Autowired
	private Producer producer;


	@Override
	public void sendMessage(Object message) {
		producer.sendMessage(message, MessageConstant.INVITATION.invitation_routing_key);

	}

}
