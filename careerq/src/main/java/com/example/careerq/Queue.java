package com.example.careerq;

import java.util.LinkedList;

public class Queue<T> {
	private LinkedList<T> people;
	private int queueSize;
	private QueueStatus status;
	
	public void setSize(int size) {
		this.queueSize = size;
	}
	
	public void pause() {
		this.status = status.PAUSED;
	}
	
	public void resume() {
		this.status = status.ACTIVE;
	}
	
	public void remove(T entity) {
		
	}
	
	public void add(T entity) {
		
	}
	
	public void prioritize(T entity) {
		
	}
}
