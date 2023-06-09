package com.example.careerq.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.model.Company;
import com.example.careerq.model.Event;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EventService {
    private JwtUtil jwtutil = JwtUtil.getInstance();
    private UserService userService = new UserService();
    private static Map<String, Event> events = new HashMap<>(); // maps eventID's to their respective event
    private Gson gson = new Gson(); // used for writing objects as json strings

    public EventService() {
        // add some default data
        Event e1 = new Event("sjsu@sjsu.edu", "San Jose State University", "09/18/2023 at 12:00", "09/18/2023 at 15:00");
        Event e2 = new Event("sfsu@sfsu.edu", "San Francisco State University", "09/23/2023 at 13:00", "09/23/2023 at 16:00");
        Event e3 = new Event("berkeley@berkeley.edu", "Berkeley", "10/02/2023 at 12:00", "09/18/2023 at 15:00");
        events.put(e1.getEventID(), e1);
        events.put(e2.getEventID(), e2);
        events.put(e3.getEventID(), e3);
    }

    // returns a list of all the events in the database where each event is a json string
    public List<String> getAllEvents() {
        List<String> allEvents = new ArrayList<>();
        for (Event ev : events.values()) {
            allEvents.add(gson.toJson(ev));
        }
        return allEvents;
    }

    public List<String> getMyEvents(String authHeader) {
        // make sure the user has a jwt
        DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
        if (decodedJWT == null) {
            return Collections.emptyList();
        }
        List<String> myEvents = new ArrayList<>();
        // get user email
        String userEmail = decodedJWT.getClaim("email").asString();
        for (Event ev : events.values()) {
            if (ev.getHost().equals(userEmail)) {
                myEvents.add(gson.toJson(ev));
            }
        }
        return myEvents;
    }

    // returns json string of the specified Event
    public String getEvent(String eventID) {
        return gson.toJson(events.get(eventID));
    }

    public Object[] removeEvent(String eventID, String authHeader) {
        // make sure the user has a jwt
        DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
        if (decodedJWT == null) {
            return new Object[]{"JWT is missing/invalid", 401};
        }
        // check that the user requesting is the host of the event
        String hostEmail = decodedJWT.getClaim("email").asString();
        if (!events.get(eventID).getHost().equals(hostEmail)) {
            return new Object[]{"You do not have permission to remove this event", 403};
        }
        // if they are, then remove the event
        events.remove(eventID);
        return new Object[]{"Event removed successfully", 200};
    }

    public void updateEvent(String eventID, Event updatedEvent) { // replace event with updatedEvent
        events.put(eventID, updatedEvent);
    }

    public Object[] createEvent(JsonObject json, String authHeader) {
        DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
        if (decodedJWT == null) {
            return new Object[]{"JWT is missing/invalid", 401};
        }
        // only School users can create events
        String hostEmail = decodedJWT.getClaim("email").asString();
        String hostName = decodedJWT.getClaim("name").asString();
        if (!userService.findByEmail(hostEmail).getUserType().equals("school")) {
            return new Object[]{"You are not allowed to create events", 401};
        }
        // check to make request has required fields
        if (!(json.has("startTime") && json.has("endTime"))) {
            return new Object[]{"Must provide a start time and end time for the event", 400};
        }
        // get the startTime, endTime
        String startTime = json.get("startTime").getAsString();
        String endTime = json.get("endTime").getAsString();
        // create the event
        Event newEvent = new Event(hostEmail, hostName, startTime, endTime);
        events.put(newEvent.getEventID(), newEvent);
        return new Object[]{"Successfully created new event", 200};
    }

    public Object[] joinEventWaitlist(String eventID, String authHeader) {
        DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
        if (decodedJWT == null) {
            return new Object[]{"JWT is missing/invalid", 401};
        }
        // make sure that the user is of type company
        String userEmail = decodedJWT.getClaim("email").asString();
        if (!userService.findByEmail(userEmail).getUserType().equals("company")) {
            return new Object[]{"You are not allowed to join this event", 401};
        }		
        // try to add the company to the event waitinglist
        Event event = events.get(eventID);
        boolean isSuccessful = event.addToWaitingList((Company)userService.findByEmail(userEmail));
        if (!isSuccessful) return new Object[]{"Failed to join event waitlist because you are you already participating", 400};
        return new Object[]{"Successfully joined the event waitlist", 200};
    }

    public Object[] acceptCompany(String eventID, String companyEmail, String authHeader) {
        DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
        if (decodedJWT == null) {
            return new Object[]{"JWT is missing/invalid", 401};
        }
        String userEmail = decodedJWT.getClaim("email").asString();
        Event event = events.get(eventID);
        if (!event.getHost().equals(userEmail)) {
            return new Object[] {"You are not allowed to do this operation", 403};
        }
        Company company = (Company)userService.findByEmail(companyEmail);
        event.removeFromWaitingList(company, true); // remove company from waitinglist and add to participatingCompanies list
        return new Object[] {"Successfully added company to the event", 200};
    }
}
