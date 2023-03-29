package com.example.careerq.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.careerq.Queue;
import com.example.careerq.model.User;
import com.example.careerq.repository.UserRepository;

public class Student extends User {
    @Autowired
    private UserRepository userRepository; 

	private String name;
	
	public void joinQueue(Company company) {
		company.addToQueue(this);
	}
	
	public void leaveQueue(Company company) {
		company.removeFromQueue(this);
	}
	
	public void getQueuePosition(Company company) {
		
	}
	
	public Queue<Student> viewQueue(Company company) { // idk if this method is even necessary
		return company.getQueue();
	}
	
	
	
}
