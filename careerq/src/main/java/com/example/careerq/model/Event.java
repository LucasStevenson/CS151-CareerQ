package com.example.careerq.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.careerq.Queue;
import com.example.careerq.entities.Company;

@Document(collection = "events")
public class Event {
	@Id
	private String eventID;
	private String host;
	private Date startTime;
	private Date endTime;
	private Queue<Company> waitingList;
	private List<Company> participatingCompanies;
	
	public void setStartTime(Date t) {
		this.startTime = t;
	}
	
	public void setEndTime(Date t) {
		this.endTime = t;
	}
	
	public void addToWaitingList(Company company) { // make sure to add this to UML diagram
		this.waitingList.add(company);
	}
	
	public void removeFromWaitingList(Company company) {
		this.waitingList.remove(company);
	}
	
	public void removeFromEvent(Company company) {
	
	}
	
	public void addToEvent(Company company) {
		this.participatingCompanies.add(company);
	}
}
