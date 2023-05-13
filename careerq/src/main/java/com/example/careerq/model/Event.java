package com.example.careerq.model;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Event {
	private String eventID;
	private String host;
	private String startTime;
	private String endTime;
	private List<Company> waitingList = new ArrayList<>();
	private List<Company> participatingCompanies = new ArrayList<>();

	public Event() {
	}

	public Event(String hostEmail, String startTime, String endTime) {
		this.host = hostEmail;
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventID = Hashing.sha256().hashString(host+startTime+endTime, StandardCharsets.UTF_8).toString().substring(0,15);
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<Company> getWaitingList() {
		return waitingList;
	}

	public List<Company> getParticipatingCompanies() {
		return participatingCompanies;
	}

}
