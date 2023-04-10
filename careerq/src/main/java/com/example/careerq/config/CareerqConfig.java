package com.example.careerq.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// this class loads in all the sensitive data from a file (config.properties)
public class CareerqConfig {
    private String dbUri;
    private String dbName;
    private String jwtSecret;
    private String jwtExpireTime;

    public CareerqConfig() {
        Properties props = new Properties();
        try (InputStream input = CareerqConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbUri = props.getProperty("mongodb.uri");
        dbName = props.getProperty("mongodb.database");
        jwtSecret = props.getProperty("jwt.secretKey");
        jwtExpireTime = props.getProperty("jwt.tokenLifeTime");
    }

    public String getMongoURI() {
        return dbUri;
    }

    public String getDBname() {
        return dbName;
    }

    public String getJWTsecret() {
        return jwtSecret;
    }
    
    public String getJWTexpireTime() {
    	return jwtExpireTime;
    }
}