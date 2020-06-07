package com.microservices.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id private String email;
    private String firstName;
	private String lastName;
	private String nickname;
	private String password;
	private String country;
	
	/**
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param nickname
	 * @param password
	 * @param country
	 */
    public User(String email, String firstName, String lastName,
    		String nickname, String password, String country) {
    	this.email = email;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    	this.password = password;
    	this.country = country;
    }
    
    public User() { super(); }
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
