package com.example.careerq.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.careerq.model.Event;
import com.mongodb.client.MongoClients;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class EventService {
	String uri;
	// datastore that will connect to the mongo database
	public final Datastore datastore = Morphia.createDatastore(MongoClients.create(uri), "CareerQ");
	public EventService() {
		// map the classes in `model` directory to actual documents in the database
		datastore.getMapper().mapPackage("model");
		datastore.ensureIndexes();	
	}
	
	// returns a list of all the events in the database
	public List<Event> getAllEvents() {
		List<Event> events= new ArrayList<>();
		Iterator<Event> it = datastore.find(Event.class).iterator();
		while (it.hasNext()) {
			events.add(it.next());
		}
		return events;
	}
}
