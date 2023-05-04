package com.example.careerq.controller;

import com.example.careerq.model.Event;
import com.example.careerq.service.EventService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static spark.Spark.*;

public class EventsController {
	private EventService eventService = new EventService();

	public void addRoutes() {
		// returns a list of all the events
		get("/events", (req, res) -> {
			return eventService.getAllEvents();
		});
		
		get("/events/:id", (req, res) -> {
			return eventService.getEvent(req.params(":id"));
		});

		// returns a list of events that a particular school hosts
		 get("/my-events", (req, res) -> {
			 return eventService.getMyEvents(req.headers("Authorization"));
		 });

		// create an event
		post("/create-event", (req, res) -> {
			JsonObject json = JsonParser.parseString(req.body()).getAsJsonObject();
			Object[] response = eventService.createEvent(json, req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});

		// removes a particular event
		delete("/remove-event/:id", (req, res) -> {
			Object[] response = eventService.removeEvent(req.params(":id"), req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});

		// accept a company on the event waitlist
		// post("", (req, res) -> {
		// on the frontend, this will just be a button that calls this endpoint
		// });
		
		// company wants to join an event
		post("/join-event/:eventID", (req, res) -> {
			Object[] response = eventService.joinEventWaitlist(req.params(":eventID"), req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});

	}

}
