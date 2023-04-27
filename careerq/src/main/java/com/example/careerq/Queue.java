package com.example.careerq;
import java.util.LinkedList;

import com.example.careerq.model.User;

enum QueueStatus {
	PAUSED,
	ACTIVE
}

public class Queue<T extends User> {
	private LinkedList<T> people;
	private int maxQueueSize;
	private QueueStatus status;
	
	public void setSize(int size) {
		this.maxQueueSize = size;
	}
	
	public void pause() {
		this.status = QueueStatus.PAUSED;
	}
	
	public void resume() {
		this.status = QueueStatus.ACTIVE;
	}
	
	public void remove(T entity) {
		
	}
	
	public void add(T entity) {
		
	}
}