package com.example.careerq.config;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.careerq.model.User;

// this class will have the methods for creating, verifying, and signing tokens JWTs
public class JwtUtil {
    private VariablesConfig config = VariablesConfig.getInstance();
    private String secretKey; // the key used to sign the tokens (READ FROM CONFIG FILE)
    private int tokenLifetime; // amount of milliseconds a token is valid (READ FROM CONFIG FILE)
    private Algorithm algorithm; // the algorithm and secret key used to sign/verify tokens
    private static JwtUtil instance; // singleton design pattern to only create one instance of this class

    private JwtUtil() {
        secretKey = config.getJWTsecret();
        tokenLifetime = config.getJWTexpireTime();
        algorithm = Algorithm.HMAC512(secretKey);
    }

    public static synchronized JwtUtil getInstance() { // multithreading safe
        if (instance == null) {
            instance = new JwtUtil();
        }
        return instance;
    }

    public String generateToken(User user) {
        // create the JWT and then add the claims to it
        return JWT.create()
            .withClaim("uID", user.getId())
            .withClaim("uType", user.getUserType()) // user type (company, school, student)
            .withClaim("email", user.getEmail()) // email of the user
            .withClaim("name", user.getName())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + tokenLifetime*1000)) // jwt expires in one hour
            .sign(algorithm);
    }

    public DecodedJWT decodeJWT(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        // verify that the jwt is valid
        String token = authorizationHeader.substring(7);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException ex) {
            return null;
        }
        return decodedJWT;
    }
}
