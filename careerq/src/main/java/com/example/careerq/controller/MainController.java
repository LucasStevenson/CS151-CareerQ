package com.example.careerq.controller;

import static spark.Spark.*;

import com.example.careerq.config.VariablesConfig;

public class MainController {
	public static void main(String[] args) {
        // Start the Spark server
        port(8080);
        
        AuthController authController = new AuthController();
        EventsController eventsController = new EventsController();
        authController.addRoutes();
        eventsController.addRoutes();
    }
}
