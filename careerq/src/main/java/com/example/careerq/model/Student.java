package com.example.careerq.model;

import com.example.careerq.Queue;

public class Student extends User {
	private String name;
	
	public Student() {}
	
	public Student(String email, String password, String name) {
		super(email, password, "student");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

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
