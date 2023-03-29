package com.example.careerq.model;

import java.util.Date;


import com.example.careerq.model.Event;
import com.example.careerq.model.User;

public class School extends User {
	private String schoolName;
	
	public School() {}
	
	public School(String email, String password, String schoolName) {
		super(email, password);
		this.schoolName = schoolName;
	}
	
	// getters and setters
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void rejectCompany(Company company, Event event) {
		
	}
	
	public void removeCompany(Company company, Event event) {
		
	}
}
