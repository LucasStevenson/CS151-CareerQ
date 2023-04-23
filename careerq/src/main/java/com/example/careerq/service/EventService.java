package com.example.careerq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.careerq.model.Event;


public class EventService {
	private static Map<String, Event> events = new HashMap<>(); // maps eventID's to their respective event
	
	// returns a list of all the events in the database
	public List<Event> getAllEvents() {
		return new ArrayList<>(events.values());
	}
	
	public Event getEvent(String eventID) {
		return events.get(eventID);
	}
	
	public void removeEvent(String eventID) {
		events.remove(eventID);
	}
	
	public void updateEvent(String eventID, Event updatedEvent) { // replace event with updatedEvent
		events.put(eventID, updatedEvent);
	}
	
	public void createEvent(Event event) {
		events.put(event.getEventID(), event);
	}
}
