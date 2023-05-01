package com.example.careerq.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.Queue;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.model.Company;
import com.example.careerq.model.Student;
import com.example.careerq.model.User;

public class QueueService {
	private UserService userService = new UserService();
	private JwtUtil jwtutil = JwtUtil.getInstance();

	public Object[] joinCompanyQueue(String companyEmail, String authHeader) {
		DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
		if (decodedJWT == null) {
			return new Object[] { "JWT is missing/invalid", 401 };
		}
		// check that the user requesting is a student
		String userEmail = decodedJWT.getClaim("email").asString();
		if (!userService.findByEmail(userEmail).getUserType().equals("student")) {
			return new Object[] { "Only students can join company queues", 403 };
		}
		// try to add the student to the company queue
		Student student = (Student)userService.findByEmail(userEmail);
		Company company = (Company)userService.findByEmail(companyEmail);
		boolean isSuccessful = company.addToQueue(student);
		if (!isSuccessful) {
			return new Object[] {"Failed to join the queue. The queue is either full, paused, or you are already in it", 405};
		}
		return new Object[] {"Successfully joined the company queue", 200};
	}
	
	public Object[] leaveCompanyQueue(String companyEmail, String authHeader) {
		DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
		if (decodedJWT == null) {
			return new Object[] { "JWT is missing/invalid", 401 };
		}
		User user = userService.findByEmail(decodedJWT.getClaim("email").asString());
		Queue<User> companyQueue = ((Company)userService.findByEmail(companyEmail)).getQueue();
		if (!companyQueue.contains(user)) {
			return new Object[] {"You are not in this queue", 405};
		}
		companyQueue.remove(user);
		return new Object[] {"Successfully left the company queue", 200};
	}
	
	public Object[] pauseQueue(String companyEmail, String authHeader) {
		DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
		if (decodedJWT == null) {
			return new Object[] { "JWT is missing/invalid", 401 };
		}
		Company company = (Company)userService.findByEmail(companyEmail);
		if (!company.getEmail().equals(decodedJWT.getClaim("email").asString())) {
			return new Object[] {"You can only pause queues you control", 403};
		}
		// pause the queue
		company.pauseQueue();
		return new Object[] {"Successfully paused the queue", 200};
	}
	
	public Object[] resumeQueue(String companyEmail, String authHeader) {
		DecodedJWT decodedJWT = jwtutil.decodeJWT(authHeader);
		if (decodedJWT == null) {
			return new Object[] { "JWT is missing/invalid", 401 };
		}
		Company company = (Company)userService.findByEmail(companyEmail);
		if (!company.getEmail().equals(decodedJWT.getClaim("email").asString())) {
			return new Object[] {"You can only resume queues you control", 403};
		}
		// resume the queue
		company.resumeQueue();
		return new Object[] {"Successfully resumed the queue", 200};
	}
}
