package com.example.careerq.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.model.Event;
import com.example.careerq.service.EventService;
import com.example.careerq.service.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

import java.util.Date;

public class EventsController {
	private EventService eventService = new EventService();

	public void addRoutes() {
		// returns a list of all the events
		get("/events", (req, res) -> {
			return eventService.getAllEvents();
		});

		// returns a list of events that a particular school hosts
		// get("/my-events", (req, res) -> {
		// we will filter what results to show based on the data in the jwt
		//
		// });

		// create an event
		post("/create-event", (req, res) -> {
			JsonObject json = JsonParser.parseString(req.body()).getAsJsonObject();
			String response = eventService.createEvent(json, req.headers("Authorization"));
			if (response.equals("JWT is missing/invalid") || response.equals("You are not allowed to create events")) {
				res.status(401);
			} else if (response.equals("Must provide a start and end time for the event")) {
				res.status(400);
			}
			res.status(200);
			return response;
		});

		// removes a particular event
		delete("/remove-event/:id", (req, res) -> {
			String response = eventService.removeEvent(req.params(":id"), req.headers("Authorization"));
			if (response.equals("JWT is missing/invalid")) {
				res.status(401);
			} else if (response.equals("You do not have permission to remove this event")) {
				res.status(403);
			}
			res.status(200);
			return response;
		});

		// accept a company on the event waitlist
		// post("", (req, res) -> {
		// on the frontend, this will just be a button that calls this endpoint
		// });

	}

}
