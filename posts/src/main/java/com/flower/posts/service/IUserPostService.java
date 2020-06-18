package com.flower.posts.service;

import java.util.List;

import com.flower.common.dto.UserPost;
import com.flower.common.exception.MyCustomException;

public interface IUserPostService {

	List<String> getDistinctUsersService() throws MyCustomException;

	List<UserPost> getUpdatedPost(int position) throws MyCustomException;
	

}
