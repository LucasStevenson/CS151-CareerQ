package com.example.careerq.service;


import com.example.careerq.model.User;
import com.mongodb.client.MongoClients;

import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.experimental.filters.Filters;


public class UserService {
	String uri;
	// datastore that will connect to the mongo database
	public final Datastore datastore = Morphia.createDatastore(MongoClients.create(uri), "CareerQ");
	public UserService() {
		// map the classes in `model` directory to actual documents in the database
		datastore.getMapper().mapPackage("model");
		datastore.ensureIndexes();	
	}
	
	// find user by email
	public User findByEmail(String email) {
		return datastore.find(User.class).filter(Filters.eq("email", email)).first();
	}
	
	
	
}
