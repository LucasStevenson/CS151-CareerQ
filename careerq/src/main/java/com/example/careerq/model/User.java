package com.example.careerq.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity(value = "users")
public class User {
	@Id
	private String id;
	private String email;
	private String password;
	
	public User() {}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
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
	
	
	
}
