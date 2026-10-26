package com.example.spring.logintracker.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LoginTrackerService {

    private final AtomicInteger loginCounter = new AtomicInteger(0);

    // Simulated business logic method
    public void recordSuccessfulLogin() {
        loginCounter.incrementAndGet();
    }

    // Getter method to fetch the current value
    public int getLoginCount() {
        return loginCounter.get();
    }
}
