package com.example.careerq.service;


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
}
