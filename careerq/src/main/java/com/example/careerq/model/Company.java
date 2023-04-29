package com.example.careerq.model;

import com.example.careerq.Queue;
import com.example.careerq.QueueStatus;

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
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String name) {
		this.companyName = name;
	}
	
	public boolean addToQueue(Student s) { // returns true if user successfully added to queue, otherwise false
		if (queue.getStatus() == QueueStatus.PAUSED) {
			return false;
		}
		User addedUser = queue.add(s);
		return addedUser == null;
	}
	
	public void removeFromQueue(Student s) {
		queue.remove(s);
	}
	
	//public void joinEvent(Event event) {
	//	event.addToWaitingList(this);
	//}
	
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
