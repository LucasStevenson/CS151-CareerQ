package com.example.careerq.model;


import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.careerq.Queue;
import com.example.careerq.model.User;

@Document (collection = "users")
@TypeAlias("student")
public class Student extends User {
	private String name;
	
	public Student() {}
	
	public Student(String email, String password, String name) {
		super(email, password);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	/*
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
	*/
	
	
	
}
