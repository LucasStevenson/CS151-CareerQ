package com.example.careerq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@PostMapping("/api/login")
	public String login() {
		return "";
	}
}
