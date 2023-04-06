package com.example.careerq.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.careerq.model.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtil {
    @Value("${jwt.secretKey}")
    private String secretKey; // the key used to sign the tokens

    @Value("${jwt.tokenLifetime}")
    private int tokenLifetime; // amount of time a token is valid

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", user.getId()); // jwt subject
        claims.put("created", new Date());
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(new Date(System.currentTimeMillis() + tokenLifetime))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
    }

    public boolean validateToken(String token) {
        // PROBABLY SHOULD REPLACE THE PRINT STATEMENTS WITH EXCEPTIONS
        // don't feel like doing it right now, but the process should be something like
        // create a custom exception, like (for example) `InvalidTokenException`
        // for all the catch statements, throw InvalidTokenException with the message
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.printf("Invalid JWT signature: %s%n", e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.printf("Invalid JWT token: %s%n", e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.printf("Expired JWT token: %s%n", e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.printf("Unsupported JWT token: %s%n", e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.printf("JWT token is empty: %s%n", e.getMessage());
        }
        return false;
    }

    public String getIdFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}
