package com.microservices.service;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservices.entity.User;
import com.microservices.repository.UserRepository;

import javassist.NotFoundException;

import java.util.List;

import javax.servlet.UnavailableException;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	private final RabbitTemplate rabbitTemplate;
	private final Exchange exchange;
	
	public UserService(RabbitTemplate rabbitTemplate, Exchange exchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchange = exchange;
	}
    
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
    
    public List<User> findByCountry(String country) {
        return userRepository.findAllByCountry(country);
    }
    
    public void add(User user) {
    	User u = userRepository.findOneByEmail(user.getEmail());
    	if (u == null) 
    		userRepository.save(user);
    	else
    		throw new DuplicateKeyException("A User with the specified email already exists");
    	sendRabbitMqEvent();
    }
    
    // Event that notifies a user has been created
    private void sendRabbitMqEvent() {
    	String routingKey = "user.created";
        String message = "user created";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, message);
    }
    
    public void update(User user) throws NotFoundException {
    	User u = userRepository.findOneByEmail(user.getEmail());
    	if (u != null)
    		userRepository.save(user);
    	else
    		throw new NotFoundException("No User was found");
    }

    @Transactional
	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
	}
    
    public void healthCheck() throws UnavailableException {
    	if (userRepository == null)
    		throw new UnavailableException("JPA Repository not initialised");
    }
    
}