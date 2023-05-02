package com.example.careerq;
import java.util.LinkedList;

import com.example.careerq.model.User;

public class Queue<T extends User> {
	private LinkedList<T> people;
	private int maxQueueSize;
	private QueueStatus status;
	
	public Queue() {
		this.maxQueueSize = 30; // default queue size. Can change using the setter method
		this.people = new LinkedList<>();
		this.status = QueueStatus.ACTIVE; // queue is by default active
	}
	
	public void setSize(int size) {
		this.maxQueueSize = size;
	}
	
	public void pause() {
		this.status = QueueStatus.PAUSED;
	}
	
	public void resume() {
		this.status = QueueStatus.ACTIVE;
	}
	
	public QueueStatus getStatus() {
		return status;
	}
	
	public boolean contains(T entity) {
		return people.contains(entity);
	}
	
	// remove a person from the queue
	public void remove(T entity) {
		people.remove(entity);
	}
	
	// add a person to the queue
	public User add(T entity) {
		if (people.size() >= maxQueueSize || people.contains(entity)) { // dont add user if the queue is full or user already in queue
			return null;
		}
		people.add(entity);
		return entity;
	}
}