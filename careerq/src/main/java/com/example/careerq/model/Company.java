package com.example.careerq.model;

import com.example.careerq.Queue;

public class Company extends User {

	private String companyName;
	private Queue<Student> queue = new Queue<>();
	
	public Company() {}
	
	public Company(String email, String password, String companyName) {
		super(email, password, "company");
		this.companyName = companyName;
	}
	
	public Queue<Student> getQueue() {
		return queue;
	}
	
	/*
	public void addToQueue(Student s) {
		queue.add(s);
	}
	
	public void removeFromQueue(Student s) {
		queue.remove(s);
	}
	
	public void joinEvent(Event event) {
		event.addToWaitingList(this);
	}
	
	public void acceptFirstStudent() {
		
	}
	
	public void setQueueSize(int size) {
		queue.setSize(size);
	}
	
	public void pauseQueue() {
		queue.pause();
	}
	
	public void resumeQueue() {
		queue.resume();
	}
	*/
	
}
