package com.example.careerq.model;


import com.example.careerq.Queue;
import com.example.careerq.model.User;

public class Student extends User {
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
