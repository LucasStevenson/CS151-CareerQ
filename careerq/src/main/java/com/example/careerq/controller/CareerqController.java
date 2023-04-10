package com.example.careerq.controller;

import com.example.careerq.service.EventService;

import static spark.Spark.*;

public class CareerqController {
	private static EventService eventService;
	
	public static void main(String[] args) {
		// returns a list of all the events
		get("/events", (req, res) -> {
			return eventService.getAllEvents();
		});
		
		// updates a particular event
		// MIGHT NEED TO ADD MIDDLEWARE TO CHECK `req` IS COMING FROM `School` USER
		post("/edit-event", (req, res) -> {
			
		})
	}
}
