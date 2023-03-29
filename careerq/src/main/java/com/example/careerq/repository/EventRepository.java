package com.example.careerq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.careerq.model.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	
}
