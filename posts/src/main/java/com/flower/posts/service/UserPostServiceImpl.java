package com.flower.posts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flower.common.client.UserPostClient;
import com.flower.common.dto.UserPost;
import com.flower.common.exception.MyCustomException;

//import lombok.extern.slf4j.Slf4j;

@Component
//@Slf4j
public class UserPostServiceImpl implements IUserPostService{
	
	 @Autowired 
	  UserPostClient userPostClient;
	 
	 @Override
	 public List<String> getDistinctUsersService() throws MyCustomException {
			/*
			 * List<UserPost> userPosts = userPostClient.getUserPost(); List<String>
			 * userPostFiltered = userPosts.stream() .filter(distinctByKey(p ->
			 * p.getUserId())) .map(UserPost::getUserId) .collect(Collectors.toList());
			 */
			  return userPostClient.getUserPost().stream() 
					  .filter(distinctByKey(p -> p.getUserId()))
					  .map(UserPost::getUserId)
					  .collect(Collectors.toList());
		}
	
	 @Override
	 public List<UserPost> getUpdatedPost(int position) throws MyCustomException {
		 List<UserPost> userPosts=new ArrayList<UserPost>();
		 
		 try {
			 userPosts = userPostClient.getUserPost();
			 UserPost userPostsAtPosition = userPosts.get(position-1);
			 userPostsAtPosition.setTitle("1800Flowers");
			 userPostsAtPosition.setBody("1800Flowers");
			 userPosts.set(position-1, userPostsAtPosition);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new MyCustomException(e.getMessage());
		}
		 
			  return userPosts;
		}
	 
	 
	 public static <T> Predicate<T> distinctByKey(
				    Function<? super T, ?> keyExtractor) {
				   
				    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
				    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
				}
	
	

}
