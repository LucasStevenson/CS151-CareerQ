package com.example.careerq.config;

import java.util.Date;

import com.example.careerq.config.CareerqConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.model.User;

// this class will have the methods for creating, verifying, and signing tokens JWTs
public class JwtUtil {
    private String secretKey; // the key used to sign the tokens (READ FROM CONFIG FILE)

    private int tokenLifetime; // amount of time a token is valid (READ FROM CONFIG FILE)
    
    private Algorithm algorithm;
    
    public JwtUtil() {
    	algorithm = Algorithm.HMAC512(secretKey);
    }

    public String generateToken(User user) {
    	// create the JWT and then add the claims to it
        return JWT.create()
        		.withClaim("uID", user.getId()) // id of the user
        		.withClaim("uType", user.getUserType()) // user type (company, school, student)
        		.sign(algorithm);
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
    	JWTVerifier verifier = JWT.require(algorithm).build();
    	return verifier.verify(token);
    }

    public String getIdFromToken(DecodedJWT decodedjwt) {
        return decodedjwt.getClaim("uID").asString();

    }
}
