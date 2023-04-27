package com.example.careerq.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.model.Company;
import com.example.careerq.model.Event;
import com.google.gson.JsonObject;

public class EventService {
	JwtUtil jwtutil = JwtUtil.getInstance();
	UserService userService = new UserService();
	private static Map<String, Event> events = new HashMap<>(); // maps eventID's to their respective event

	// returns a list of all the events in the database
	public List<Event> getAllEvents() {
		return new ArrayList<>(events.values());
	}

	public Event getEvent(String eventID) {
		return events.get(eventID);
	}

	public String removeEvent(String eventID, String authHeader) {
		// make sure the user has a jwt
		DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
		if (decodedJWT == null) {
			return "JWT is missing/invalid";
		}
		// check that the user requesting is the host of the event
		String hostEmail = decodedJWT.getClaim("email").asString();
		if (!this.getEvent(eventID).getHost().equals(hostEmail)) {
			return "You do not have permission to remove this event";
		}
		// if they are, then remove the event
		events.remove(eventID);
		return "Event removed successfully";
	}

	public void updateEvent(String eventID, Event updatedEvent) { // replace event with updatedEvent
		events.put(eventID, updatedEvent);
	}

	public String createEvent(JsonObject json, String authHeader) {
		DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
		if (decodedJWT == null) {
			return "JWT is missing/invalid";
		}
		// only School users can create events
		String hostEmail = decodedJWT.getClaim("email").asString();
		if (!userService.findByEmail(hostEmail).getUserType().equals("school")) {
			return "You are not allowed to create events";
		}
		// check to make request has required fields
		if (!(json.has("startTime") && json.has("endTime"))) {
			return "Must provide a start and end time for the event";
		}
		// going to assume times are given as strings in the format: MM/DD/YYYY
		Date startTime = new Date(json.get("startTime").getAsString());
		Date endTime = new Date(json.get("endTime").getAsString());
		// create the event
		Event newEvent = new Event(hostEmail, startTime, endTime);
		events.put(newEvent.getEventID(), newEvent);
		return "Successfully created new event";
	}
}
