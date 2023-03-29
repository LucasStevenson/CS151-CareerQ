package com.example.careerq.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.careerq.entities.Company;
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
		event.addToWaitingList(company); // first, modify the `waitingList` list in the event class
		eventRepository.save(event); // save changes to db
	}

	public void removeFromWaitingList(Event event, Company company) {
		event.removeFromWaitingList(company);
		eventRepository.save(event);
	}

	public void removeFromEvent(Event event, Company company) {
		event.removeFromEvent(company);
		eventRepository.save(event);
	}

	public void addToEvent(Event event, Company company) {
		event.addToEvent(company);
		eventRepository.save(event);
	}
}
