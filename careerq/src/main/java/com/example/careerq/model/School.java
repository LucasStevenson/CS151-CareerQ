package com.example.careerq.model;

public class School extends User {
	public School() {}
	
	public School(String email, String password, String name) {
		super(email, name, password, "school");
	}
}
