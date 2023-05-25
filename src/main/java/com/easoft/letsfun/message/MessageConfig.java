package com.easoft.letsfun.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

	private final String EXCHANGE = "exchange";

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabitTemplate = new RabbitTemplate(connectionFactory);
		rabitTemplate.setMessageConverter(converter());
		return rabitTemplate;
	}

	/* invitation definition */

	@Bean
	public Queue invitation_queue() {
		return new Queue(MessageConstant.INVITATION.invitation_queue);
	}

	@Bean
	public Binding invitation_binding() {
		return BindingBuilder.bind(invitation_queue()).to(exchange())
				.with(MessageConstant.INVITATION.invitation_routing_key);
	}

	/* invitation definition */
}
