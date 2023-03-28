package com.example.careerq.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.careerq.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
