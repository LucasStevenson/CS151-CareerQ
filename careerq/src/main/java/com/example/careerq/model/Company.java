package com.example.careerq.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.careerq.Queue;

@Document (collection = "users")
@TypeAlias("company")
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
