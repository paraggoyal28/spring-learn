package com.example.spring.logintracker;

import com.example.spring.logintracker.service.LoginTrackerService;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "loginstats") // This sets the URL path to /actuator/logistics
public class LoginStatsEndpoint {
    private final LoginTrackerService loginTrackerService;

    // Injecting our Spring bean via constructor injection
    public LoginStatsEndpoint(LoginTrackerService loginTrackerService) {
        this.loginTrackerService = loginTrackerService;
    }

    @ReadOperation // Map this method to an HTTP GET request
    public Map<String, Object> getLoginStatistics() {
        Map<String, Object> details = new HashMap<>();

        details.put("status", "UP");
        details.put("totalLoginAttempts", loginTrackerService.getLoginCount());
        details.put("timestamp", System.currentTimeMillis());

        return details;
    }

}
