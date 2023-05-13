package com.example.careerq.model;

import com.example.careerq.Queue;
import com.example.careerq.QueueStatus;

public class Company extends User {
	private Queue<User> queue = new Queue<>();
	
	public Company() {}
	
	public Company(String email, String password, String name) {
		super(email, name, password, "company");
	}
	
	public Queue<User> getQueue() {
		return queue;
	}
	
	public boolean addToQueue(Student s) { // returns true if user successfully added to queue, otherwise false
		if (queue.getStatus() == QueueStatus.PAUSED) {
			return false;
		}
		User addedUser = queue.add(s);
		return addedUser != null;
	}
	
	public void removeFromQueue(Student s) {
		queue.remove(s);
	}
	
	//public void joinEvent(Event event) {
	//	event.addToWaitingList(this);
	//}
	
	public void acceptFirstStudent() {
        queue.removeFirst();
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
