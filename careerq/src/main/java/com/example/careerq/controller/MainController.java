package com.example.careerq.controller;

import static spark.Spark.*;

public class MainController {
	public static void main(String[] args) {
		// Start the Spark server
		port(8080);

		// cors stuff
		options("/*", (req, res) -> {
			String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
			}

	        return "OK";
		});

		before((req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Headers", "*");
			res.type("application/json");
		});
		
		
		AuthController authController = new AuthController();
		EventsController eventsController = new EventsController();
		QueueController queueController = new QueueController();
		authController.addRoutes();
		eventsController.addRoutes();
		queueController.addRoutes();
	}
}