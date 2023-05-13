package com.example.careerq.model;

import com.example.careerq.Queue;

public class Student extends User {
	public Student() {}
	
	public Student(String email, String password, String name) {
		super(email, name, password, "student");
	}

	public void joinQueue(Company company) {
		company.addToQueue(this);
	}
	
	public void leaveQueue(Company company) {
		company.removeFromQueue(this);
	}
	
	public void getQueuePosition(Company company) {
		
	}
	
	public Queue<User> viewQueue(Company company) { // idk if this method is even necessary
		return company.getQueue();
	}
	
}
