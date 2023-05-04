package com.example.careerq.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.exceptions.ExceptionHandler;
import com.example.careerq.model.Company;
import com.example.careerq.model.School;
import com.example.careerq.model.Student;
import com.example.careerq.model.User;
import com.example.careerq.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static spark.Spark.*;

import org.mindrot.jbcrypt.BCrypt;

public class AuthController {
    private UserService userService = new UserService();
    private JwtUtil jwtutil = JwtUtil.getInstance();
    private ExceptionHandler exceptionHandler = new ExceptionHandler();
    
    public void addRoutes() {
    	post("/login", (req, res) -> {
    		JsonObject json = JsonParser.parseString(req.body()).getAsJsonObject();
    	    String email = json.get("email").getAsString();
    	    String password = json.get("password").getAsString();
    	    // check if the email exists
    	    User user = userService.findByEmail(email);
    	    if (user == null) {
    	        res.status(400);
    	        return "Email doesn't exist";
    	    }
    	    try {
    	        // check to see if the password is valid
    	        if (!BCrypt.checkpw(password, user.getPassword())) {
    	            res.status(400);
    	            return "Invalid password";
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        res.status(500);
    	        return "Error while trying to validate password";
    	    }
    	    // create and assign a token that is valid for some amount of time (TBD)
    	    String token = jwtutil.generateToken(user);
    	    res.status(200);
    	    return token;
        });

        post("/register", (req, res) -> {
        	JsonObject json = JsonParser.parseString(req.body()).getAsJsonObject();
        	// validate the req data (prolly use helper method and pass in req data as json object)
    	    String msg = exceptionHandler.registerValidation(json);
    	    if (!msg.equals("Success")) {
    	    	res.status(400);
    	    	return msg;
    	    }
    	    String email = json.get("email").getAsString();
    	    String password = json.get("password").getAsString();
    	    String name = json.get("name").getAsString();
    	    String userType = json.get("userType").getAsString();
            // check if email is already in database
    		if (userService.findByEmail(email) != null) {
    			res.status(400);
    			return "Email already exists";
    		}
            // hash the password
    		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            // create a new user and save to db
    		switch (userType) {
    			case "student":
    				userService.save(new Student(email, hashedPassword, name));
    				break;
    			case "company":
    				userService.save(new Company(email, hashedPassword, name));
    				break; 
    			case "school":
    				userService.save(new School(email, hashedPassword, name));
    				break;
    		}
    		res.status(200);
    		return "Successfully created account";
        });
    }
}
