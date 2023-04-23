package com.example.careerq.model;

import java.util.Random;

public class User {
	private String id;
	private String email;
	private String password;
	private String userType;
	
	public User() {}
	
	public User(String email, String password, String userType) {
		this.id = Integer.toHexString(new Random().nextInt(1000000000) + 1); // generate number between 1 and 1B. convert into into hexadecimal string
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", userType=" + userType + "]";
	}
	
}
