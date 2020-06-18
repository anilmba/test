package com.flower.common.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.flower.common.dto.UserPost;
import com.flower.common.exception.MyCustomException;

@RunWith(SpringRunner.class)
public class UserPostClientTest {
	
	
	  UserPostClient userPostClient= new UserPostClient() ;
	  
	  @Mock 
	  private RestTemplate restTemplate;
	  
		@Rule
		public ExpectedException thrown = ExpectedException.none();
	 
	
	
	  @Before 
	  public void setUp() {
		  MockitoAnnotations.initMocks(this);
		  ReflectionTestUtils.setField(userPostClient, "restTemplate", restTemplate); 
	  }
	 
	@SuppressWarnings("deprecation")
	@Test
	public void testGetRecommendedLocationsForLoadLane() throws MyCustomException {		
		List<UserPost> userPostList = new ArrayList<UserPost>();
		userPostList.add(new UserPost());
		String url = "http://jsonplaceholder.typicode.com/posts";
		when(restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserPost>>() {
        })).thenReturn(new ResponseEntity<List<UserPost>>(userPostList, HttpStatus.OK));
		userPostList = userPostClient.getUserPost(); 
		assertNotNull(userPostList);
		assertEquals(userPostList.size(),1);
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected=MyCustomException.class)
	public void testGetRecommendedLocationsForLoadLaneServerException() throws MyCustomException {		
		List<UserPost> userPostList = new ArrayList<UserPost>();
		userPostList.add(new UserPost());
		String url = "http://jsonplaceholder.typicode.com/posts";
		when(restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserPost>>() {
        })).thenThrow(new RestClientException("TestException"));
		userPostList = userPostClient.getUserPost(); 
	}
	
	@Test
	public void testGetRecommendedLocationsForLoadLane204Exception() throws MyCustomException {		
		List<UserPost> userPostList = new ArrayList<UserPost>();
		userPostList.add(new UserPost());
		String url = "http://jsonplaceholder.typicode.com/posts";
		when(restTemplate.exchange(url,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserPost>>() {
        })).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
		 
		thrown.expect(MyCustomException.class);
		userPostList = userPostClient.getUserPost();
	}
}
