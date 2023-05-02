package com.example.careerq.controller;

import static spark.Spark.*;

import com.example.careerq.service.QueueService;

public class QueueController {
	private QueueService queueService = new QueueService();
	
	public void addRoutes() {
		// URL would look like: http://localhost:8080/joinCompanyQueue?companyEmail=company@company.com
		post("/joinCompanyQueue", (req, res) -> {
			Object[] response = queueService.joinCompanyQueue(req.queryParams("companyEmail"), req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});
		
		post("/leaveCompanyQueue", (req, res) -> {
			Object[] response = queueService.leaveCompanyQueue(req.queryParams("companyEmail"), req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});
		
		post("/resumeQueue", (req, res) -> {
			Object[] response = queueService.resumeQueue(req.queryParams("companyEmail"), req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});
		
		post("/pauseQueue", (req, res) -> {
			Object[] response = queueService.pauseQueue(req.queryParams("companyEmail"), req.headers("Authorization"));
			res.status((Integer)response[1]);
			return (String)response[0];
		});
	}
}
