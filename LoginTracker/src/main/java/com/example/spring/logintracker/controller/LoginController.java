package com.example.spring.logintracker.controller;

import com.example.spring.logintracker.service.LoginTrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginTrackerService loginTrackerService;

    // Inject the tracker service bean
    public LoginController(LoginTrackerService loginTrackerService) {
        this.loginTrackerService = loginTrackerService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        // 1. (Put your actual authenticated logic here)
        System.out.println("Inside login method");
        // 2. Increment the bean's counter upon successful verification
        loginTrackerService.recordSuccessfulLogin();

        return ResponseEntity.ok("Login Successful");
    }
}
