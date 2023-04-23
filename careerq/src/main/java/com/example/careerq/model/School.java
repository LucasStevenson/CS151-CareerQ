package com.example.careerq.model;

import java.util.Date;

public class School extends User {
	private String schoolName;
	
	public School() {}
	
	public School(String email, String password, String schoolName) {
		super(email, password, "school");
		this.schoolName = schoolName;
	}
	
	// getters and setters
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
}
