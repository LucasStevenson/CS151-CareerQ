package com.example.careerq.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.example.careerq.Queue;


public class Event {
	private String eventID;
	private String host;
	private Date startTime;
	private Date endTime;
	private Queue<Company> waitingList = new Queue<>();
	private List<Company> participatingCompanies = new ArrayList<>();
	
	public Event() {}
	
	public Event(String host, Date startTime, Date endTime) {
		this.eventID = Integer.toHexString(new Random().nextInt(1000000000) + 1); // generate number between 1 and 1B. convert into into hexadecimal string 
		this.host = host;
		this.startTime = startTime;
		this.endTime = endTime;
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
	
	public Queue<Company> getWaitingList() {
		return waitingList;
	}
	
	public List<Company> getParticipatingCompanies() {
		return participatingCompanies;
	}

}
