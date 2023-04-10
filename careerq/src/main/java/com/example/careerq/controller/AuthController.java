package com.example.careerq.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.careerq.config.JwtUtil;
import com.example.careerq.model.User;
import com.example.careerq.service.EventService;
import com.example.careerq.service.UserService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static spark.Spark.*;

import org.mindrot.jbcrypt.BCrypt;

// THIS CODE DOES NOT CURRENTLY WORK AND IS STILL A WORK IN PROGRESS

/* Todo:
 * Write the code for registering. Already put the process in comments, just need to turn into java code
 */

public class AuthController {
    private static UserService userService;
    private static JwtUtil jwtutil;

    public static void main(String[] args) {
        post("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");

            // check if the email exists
            User user = userService.findByEmail(email);
            if (user == null) {
                res.status(400);
                return "Email doesn't exist";
            }

            // check to see if the password is valid
            if (!BCrypt.checkpw(password, user.getPassword())) {
                res.status(400);
                return "Invalid password";
            }

            // create and assign a token that is valid for some amount of time (TBD)
            String token = jwtutil.generateToken(user);

            res.status(200);
            return token;
        });

        post("/register", (req, res) -> {
            // validate the req data (prolly use helper method and pass in req data as json object)

            // check if user is already in database

            // hash the password

            // create a new user object with the user details. make sure to use hashed password

            // save user object to database
        })
    }
}
