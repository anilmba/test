package com.flower.common.client;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.flower.common.dto.UserPost;
import com.flower.common.exception.MyCustomException;

//import lombok.extern.slf4j.Slf4j;

@Component
//@Slf4j
public class UserPostClient {



	@Autowired
	private RestTemplate restTemplate;
	private static final String CLASS_NAME = "UserPostClient";


	public List<UserPost> getUserPost() throws MyCustomException {

	
		String url = "http://jsonplaceholder.typicode.com/posts";
		
		List<UserPost> userPosts=new ArrayList<UserPost>();
		
		try {
			 
			  ResponseEntity<List<UserPost>> response =
				        restTemplate.exchange(url,
				                    HttpMethod.GET, null, new ParameterizedTypeReference<List<UserPost>>() {
				            });

			if (null != response && (HttpStatus.Series.valueOf(response.getStatusCode()) == HttpStatus.Series.SUCCESSFUL)) {
				Optional<List<UserPost>> userPostListDtoResponse = ofNullable(response.getBody());
				if (userPostListDtoResponse.isPresent()) {
					userPosts= userPostListDtoResponse.get();
				}
				if(userPosts.size()==0) {
					throw new MyCustomException(Response.Status.NOT_FOUND.getStatusCode(),204,"Service Not available");
				}
			}
		} catch (Exception e) {
			throw new MyCustomException(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(),500,"Service Not available");
			}

		return userPosts;
	}

}
