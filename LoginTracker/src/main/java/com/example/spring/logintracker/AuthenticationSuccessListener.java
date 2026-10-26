package com.example.spring.logintracker;

import com.example.spring.logintracker.service.LoginTrackerService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final LoginTrackerService loginTrackerService;

    public AuthenticationSuccessListener(LoginTrackerService loginTrackerService) {
        this.loginTrackerService = loginTrackerService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // Automatically increments the bean whenever any user logs in successfully
        loginTrackerService.recordSuccessfulLogin();
    }
}
