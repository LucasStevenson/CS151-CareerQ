package com.example.careerq.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.model.Event;
import com.example.careerq.service.EventService;
import com.example.careerq.service.UserService;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class EventsController {
	private EventService eventService = new EventService();
	private JwtUtil jwtutil = JwtUtil.getInstance();
	private UserService userService = new UserService();

	public void addRoutes() {
		// returns a list of all the events
		get("/events", (req, res) -> {
			return eventService.getAllEvents();
		});
		
		// create an event
		post("/create-event", (req, res) -> { // TODO: finish this
			DecodedJWT decodedJWT = this.decodeJWT(req.headers("Authorization"));
			if (decodedJWT == null) {
				res.status(401);
				return "JWT is missing/invalid";
			}
			// only School users can create events
			String userEmail = decodedJWT.getClaim("email").asString();
			if (!userService.findByEmail(userEmail).getUserType().equals("school")) {
				res.status(401);
				return "You are not allowed to create events";
			}
			return "poggers!";
			// create the event
			//Event newEvent = new Event(userEmail, startTime, endTime);
		});

		// removes a particular event
		delete("/remove-event/:id", (req, res) -> {
			// make sure the user has a jwt
			DecodedJWT decodedJWT = this.decodeJWT(req.headers("Authorization"));
			if (decodedJWT == null) {
				res.status(401);
				return "JWT is missing/invalid";
			}

			// check that the user requesting is the host of the event
			String eventID = req.params(":id");
			String hostEmail = decodedJWT.getClaim("email").asString();
			if (!eventService.getEvent(eventID).getHost().equals(hostEmail)) {
				res.status(403);
				return "You do not have permission to remove this event";
			}
			// if they are, then remove the event
			eventService.removeEvent(eventID);
			res.status(200);
			return "Event removed successfully";
		});
	}
	
	private DecodedJWT decodeJWT(String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			return null;
		}
		// verify that the jwt is valid
		String token = authorizationHeader.substring(7);
		DecodedJWT decodedJWT;
		try {
			decodedJWT = jwtutil.validateToken(token);
		} catch (JWTVerificationException ex) {
			return null;
		}
		return decodedJWT;
	}
		
}
