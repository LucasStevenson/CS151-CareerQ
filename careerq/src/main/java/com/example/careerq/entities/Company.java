package com.example.careerq.entities;

import com.example.careerq.Queue;
import com.example.careerq.model.User;
import com.example.careerq.model.Event;

public class Company extends User {
	private String companyName;
	private Queue<Student> queue;
	
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
	
}
