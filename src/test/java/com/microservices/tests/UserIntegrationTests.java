package com.microservices.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetAndPostRestServices() throws Exception {
	  mockMvc.perform(
            MockMvcRequestBuilders.get("/users"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
        
        User user = new User("marco@email.com", "Marco", "Furone", "NickName", "123", "UK");
        mockMvc.perform( MockMvcRequestBuilders
            	.post("/users")
                .content(new ObjectMapper().writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
    		    .accept(MediaType.APPLICATION_JSON))
    		    .andExpect(status().isOk());
	  
        mockMvc.perform(
              MockMvcRequestBuilders.get("/users"))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].firstName").value(Lists.newArrayList("Marco")));
  }

}