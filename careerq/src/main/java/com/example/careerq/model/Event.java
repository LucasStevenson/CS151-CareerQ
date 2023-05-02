package com.example.careerq.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Event {
	private String eventID;
	private String host;
	private Date startTime;
	private Date endTime;
	private List<Company> waitingList = new ArrayList<>();
	private List<Company> participatingCompanies = new ArrayList<>();

	public Event() {
	}

	public Event(String host, Date startTime, Date endTime) {
		this.eventID = Integer.toHexString(new Random().nextInt(1000000000) + 1); // generate number between 1 and 1B.
																					// convert into into hexadecimal
																					// string
		this.host = host;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// removes a specific company from an event
	public void removeCompany(Company company) {
		participatingCompanies.remove(company);
	}

	// removes a company from the waitlist and either adds to event or doesn't
	// whether or not we add depends on the second boolean parameter
	public void removeFromWaitingList(Company company, boolean addToEvent) {
		waitingList.remove(company);
		if (addToEvent) {
			participatingCompanies.add(company);
		}
	}
	
	// adds a company to the waitingList
	public boolean addToWaitingList(Company company) { // returns true if successful
		if (waitingList.contains(company) || participatingCompanies.contains(company)) {
			return false;
		}
		waitingList.add(company);
		return true;
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

	public List<Company> getWaitingList() {
		return waitingList;
	}

	public List<Company> getParticipatingCompanies() {
		return participatingCompanies;
	}

}
