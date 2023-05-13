package com.example.careerq.service;

import java.util.HashMap;
import java.util.Map;

import com.example.careerq.model.Company;
import com.example.careerq.model.School;
import com.example.careerq.model.Student;
import com.example.careerq.model.User;

public class UserService {
    private static Map<String, User> users = new HashMap<>(); // maps emails to Users

    public UserService() {
        // add some default data
        School schoolUser = new School("sjsu@sjsu.edu", "$2a$10$l4YN838ABsogjUSEupOW0O3TpR3Lp5h8oNx.b8sLpoK9wd9QGtGnK", "SJSU"); // password: Password123!
        Student studentUser = new Student("student@sfsu.edu", "$2a$10$RoEy/d7Y9lraK1DQtOjbeeK0gGE1yJg/lU3G0ZaphfKz5yN09aCm6", "John"); // password: Password123!
        Company companyUser = new Company("google@google.com", "$2a$10$mI6uW5AU8.5kb1t3LisyXOMOxlLpA0ZW240RSUknprIgcu/OF27cS", "Google"); // password: Password123!
        users.put(schoolUser.getEmail(), schoolUser);
        users.put(studentUser.getEmail(), studentUser);
        users.put(companyUser.getEmail(), companyUser);
    }

    public User findByEmail(String email) {
        return users.get(email);
    }

    public User save(User user) {
        users.put(user.getEmail(), user);
        //users.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("Saved new user: " + user.getEmail() + ", " + user.getPassword());
        return user;
    }
}
