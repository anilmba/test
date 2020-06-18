package com.flower.posts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.common.client.UserPostClient;
import com.flower.common.dto.UserPost;
import com.flower.common.exception.MyCustomException;
import com.flower.posts.service.IUserPostService;

//import lombok.extern.slf4j.Slf4j;

@Component
//@Slf4j
@RestController
@RequestMapping(path = "/userposts")
public class UserPostController {
	
	  @Autowired 
	  UserPostClient userPostClient;
	  
	  @Autowired
	  IUserPostService userPostService;
	  
	  @GetMapping(path="/count", produces = "application/json")
	  public int  getCount() throws MyCustomException {
		  
		  return userPostClient.getUserPost().size();
		  
		  }
	  
	  @GetMapping(path="/users", produces = "application/json")
	  public List<String>  getDistinctUsers() throws MyCustomException {
		  
		  return userPostService.getDistinctUsersService();
		  
		  }

	  @GetMapping(path="/updatedusers/{position}", produces = "application/json")
	  public List<UserPost>  getUpdatedUsers(@PathVariable("position") int position) throws MyCustomException {
		  
		  return userPostService.getUpdatedPost(position);
		  
		  }
	  
	  
	  
	 }
