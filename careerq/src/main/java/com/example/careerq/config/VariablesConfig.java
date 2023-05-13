package com.example.careerq.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// this class loads in all the sensitive data from a file (config.properties)
public class VariablesConfig {
    private String jwtSecret;
    private String jwtExpireTime;
    private static VariablesConfig instance;


    private VariablesConfig() {
        Properties props = new Properties();
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/config.properties");

        try (InputStream input = new FileInputStream(file)) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jwtSecret = props.getProperty("jwt.secretKey");
        jwtExpireTime = props.getProperty("jwt.tokenLifetime");
    }

    public static synchronized VariablesConfig getInstance() { // multithreading safe
        if (instance == null) {
            instance = new VariablesConfig();
        }
        return instance;
    }

    public String getJWTsecret() {
        return jwtSecret;
    }

    public int getJWTexpireTime() {
        return Integer.parseInt(jwtExpireTime);
    }
}
