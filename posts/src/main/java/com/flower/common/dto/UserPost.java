package com.flower.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import lombok.Data;

//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPost implements Serializable  {

	private static final long serialVersionUID = -2284588259027350009L;
	String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	String  id;
	String  title;
	String 	body;
}