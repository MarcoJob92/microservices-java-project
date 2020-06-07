package com.microservices.controller;

import java.util.List;

import javax.servlet.UnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.entity.User;
import com.microservices.service.UserService;

import javassist.NotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * @return the List of all the Users 
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	/**
	 * @param email
	 * @return a User by email
	 */
	@GetMapping("/users/email/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
	
	/**
	 * @param country
	 * @return a List of User by country
	 */
	@GetMapping("/users/country/{country}")
	public List<User> getUsersByCountry(@PathVariable String country) {
		return userService.findByCountry(country);
	}
	
	/**
	 * Add User
	 * @param user
	 * @return 200 if user was successfully added to DB, 409 if a user with the same key already exists
	 */
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		try {
			userService.add(user);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<Error>(HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Update User
	 * @param user
	 * @return 200 if user was successfully updated, 404 if the user was not found
	 */
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		try {
			userService.update(user);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Error>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Delete User by email
	 * @param email
	 */
	@DeleteMapping("/users/{email}")
	public void deleteUser(@PathVariable String email) {
		userService.deleteByEmail(email);
	}
	
	/**
	 * Health Check
	 * @return 200 if healthy otherwise 503
	 */
	@GetMapping("/users/healthcheck")
	public ResponseEntity<?> healthCheck() {
		try {
			userService.healthCheck();
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (UnavailableException e) {
			return new ResponseEntity<Error>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
}