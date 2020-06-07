package com.microservices.event_producer;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservices.service.UserService;

@Configuration
public class EventProducerConfiguration {

	 @Bean
	 public Exchange eventExchange() {
	   return new TopicExchange("eventExchange");
	 }
	
	 @Bean
	 public UserService userService(RabbitTemplate rabbitTemplate, Exchange senderTopicExchange) {
	   return new UserService(rabbitTemplate, senderTopicExchange);
	 }
	 
}
