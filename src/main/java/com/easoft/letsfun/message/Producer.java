package com.easoft.letsfun.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(Object message,String routingKey) {
		rabbitTemplate.convertAndSend(MessageConstant.EXCHANGE, routingKey, message);
	}

}
