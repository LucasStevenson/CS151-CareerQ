package com.example.careerq.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity(value = "users")
public class User {
	@Id
	private String id;
	private String email;
	private String password;
	private String userType;
	
	public User() {}
	
	public User(String email, String password, String userType) {
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	// getters and setters
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
