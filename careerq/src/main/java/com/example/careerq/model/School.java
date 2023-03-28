package com.example.careerq.model;

import java.util.Date;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "users")
@TypeAlias("school")
public class School extends User {
	private String schoolName;
	
	public void createEvent() {
		
	}
	
	public void cancelEvent(Event event) {
		
	}
	
	public void setEventTimeFrame(Event event, Date startTime, Date endTime) {
		
	}
	
	public void addCompany(Company company, Event event) {
		
	}
	
	public void rejectCompany(Company company, Event event) {
		
	}
	
	public void removeCompany(Company company, Event event) {
		
	}
}
