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
			Object[] response = eventService.createEvent(json, req.headers("Authorization"));
			String msg = (String)response[0];
			int errCode = (Integer) response[1];
			res.status(errCode);
			return msg;
		});

		// removes a particular event
		delete("/remove-event/:id", (req, res) -> {
			Object[] response = eventService.removeEvent(req.params(":id"), req.headers("Authorization"));
			String msg = (String) response[0];
			int errCode = (Integer) response[1];
			res.status(errCode);
			return msg;
		});

		// accept a company on the event waitlist
		// post("", (req, res) -> {
		// on the frontend, this will just be a button that calls this endpoint
		// });

	}

}
