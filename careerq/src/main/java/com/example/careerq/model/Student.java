package com.example.careerq.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "users")
@TypeAlias("student")
public class Student extends User {

}
