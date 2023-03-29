package com.example.careerq.entities;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.careerq.model.Event;
import com.example.careerq.model.User;
import com.example.careerq.repository.EventRepository;
import com.example.careerq.repository.UserRepository;

public class School extends User {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

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
	
	
	public void createEvent(Event event) {
		eventRepository.save(event);
	}
	
	public void cancelEvent(Event event) {
		if (!event.getHost().equals(this.getId())) {
			// YOU CANT DO THAT
			// schools can only delete events they are hosting
		}
		eventRepository.delete(event);
	}
	
	public void setEventTimeframe(Event event, Date startTime, Date endTime) {
		// sets the start and end time of a particular event
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		eventRepository.save(event);
	}
	
	public void addCompany(Company company, Event event) {
		// adds the specified company to the specified event
		event.addToEvent(company);
		eventRepository.save(event);
	}
	
	public void rejectCompany(Company company, Event event) {
		
	}
	
	public void removeCompany(Company company, Event event) {
		
	}
}
