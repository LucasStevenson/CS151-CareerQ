package com.example.careerq.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.careerq.model.Company;
import com.example.careerq.model.Event;
import com.example.careerq.repository.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	
	public Optional<Event> getEventById(String id) {
		return eventRepository.findById(id);
	}

	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	public void addToWaitingList(Event event, Company company) {
		event.getWaitingList().add(company); // first, modify the `waitingList` list in the event class
		eventRepository.save(event); // save changes to db
	}

	public void removeFromWaitingList(Event event, Company company) {
		event.getWaitingList().remove(company);
		eventRepository.save(event);
	}

	public void removeFromEvent(Event event, Company company) {
		event.getParticipatingCompanies().remove(company);
		eventRepository.save(event);
	}

	public void addToEvent(Event event, Company company) {
		event.getWaitingList().remove(company); // remove it from the waiting list first
		event.getParticipatingCompanies().add(company); // then add the company to the list of participating companies
		eventRepository.save(event);
	}
}
