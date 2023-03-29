package com.example.careerq.model;

import java.util.ArrayList;
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
	
	public Event() {}
	
	public Event(String host, Date startTime, Date endTime) {
		this.host = host;
		this.startTime = startTime;
		this.endTime = endTime;
		this.waitingList = new Queue<Company>();
		this.participatingCompanies = new ArrayList<Company>();
	}
	
	public void addToWaitingList(Company company) { // make sure to add this to UML diagram
		this.waitingList.add(company);
	}
	
	public void removeFromWaitingList(Company company) {
		this.waitingList.remove(company);
	}
	
	public void removeFromEvent(Company company) {
		this.participatingCompanies.remove(company);
	}
	
	public void addToEvent(Company company) {
		this.participatingCompanies.add(company);
	}
	
	// getters and setters
	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
