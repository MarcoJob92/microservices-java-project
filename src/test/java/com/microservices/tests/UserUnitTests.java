package com.microservices.tests;

import static org.junit.Assert.fail;

import java.util.List;

import javax.servlet.UnavailableException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.entity.User;
import com.microservices.service.UserService;

import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserUnitTests {
	
	@Autowired
    private UserService userService;
	
	String johnEmail = "John@black.com";
	String marcoEmail = "Marco@f.com";
	String usContry = "US";
	
	@Test
    public void testHibernateCreatesUsers_testDupliacateKeys_testHibernateFindsUsers_testHibernateUpdatesUser_testHibernateDeleteUser() throws NotFoundException {
		// Create
		List<User> users = userService.findAll();
		Assert.assertEquals(0, users.size());
		
		User marco = new User(marcoEmail, "Marco", "F", "myNickName", "123", "UK");
		userService.add( marco );
		
		users = userService.findAll();
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(marcoEmail, users.get(0).getEmail());
        
        User john = new User(johnEmail, "John", "Black", "john-nick", "abc", usContry);
        userService.add( john );

        users = userService.findAll();
        Assert.assertEquals(2, users.size());
        
        // Duplicate objects are not allowed
        try {
        	userService.add( john );
			fail();
		} catch (DuplicateKeyException e) {}
        
		// Find
		List<User> usUsers= userService.findByCountry(usContry);
		Assert.assertEquals(1, usUsers.size());
		Assert.assertEquals(usContry, usUsers.get(0).getCountry());
		
		User userJohn = userService.findByEmail(johnEmail);
		Assert.assertEquals(johnEmail, userJohn.getEmail());
		Assert.assertEquals("Black", userJohn.getLastName());
        
		// Update
        john.setLastName("White");
        userService.update(john);
        userJohn = userService.findByEmail(johnEmail);
        Assert.assertEquals("White", userJohn.getLastName());
        
        // Delete
        userService.deleteByEmail(johnEmail);
        users = userService.findAll();
        Assert.assertEquals(1, users.size());
        Assert.assertFalse(users.get(0).getEmail().equals(johnEmail));
    }
	
	@Test
    public void testHealthCheck() {
		try {
			userService.healthCheck();
		} catch (UnavailableException e) {
			fail();
		}
	}

}
