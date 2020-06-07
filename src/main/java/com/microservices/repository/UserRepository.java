package com.microservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public List<User> findAllByCountry(String country);
	public User findOneByEmail(String email);
	public void deleteByEmail(String email);

}

