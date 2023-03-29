package com.example.careerq;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.careerq.model.School;
import com.example.careerq.model.Event;

import com.example.careerq.model.User;
import com.example.careerq.repository.EventRepository;
import com.example.careerq.repository.UserRepository;

@SpringBootApplication
public class CareerqApplication implements CommandLineRunner {
	//@Autowired
	//private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CareerqApplication.class, args);
	}
	
	
	public void run(String ... args) throws Exception {
		//eventRepository.deleteAll();
		userRepository.deleteAll();
		Event e1 = new Event("SJSU", new Date(2023, 9, 18), new Date(2023, 9, 19));
		School sjsu = new School("sjsu@sjsu.edu", "password123", "SJSU");
		userRepository.save(sjsu);
	}

}
