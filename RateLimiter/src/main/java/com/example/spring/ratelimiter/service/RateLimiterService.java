package com.example.spring.ratelimiter.service;

import com.example.spring.ratelimiter.model.RateLimitWindow;
import com.example.spring.ratelimiter.model.UserTier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    // In-Memory store; Key=userId, Value=Current Window Tracker
    private final ConcurrentHashMap<String, RateLimitWindow> storage
            = new ConcurrentHashMap<>();

    private static final long WINDOW_SIZE_MS = 60_000; // 1 MINUTE WINDOW

    public boolean allowRequest(String userId, UserTier tier) {
        long currentTime = System.currentTimeMillis();

        // Compute or update the rate limit window atomically per user
        RateLimitWindow window = storage.compute(userId, (key, existingWindow) -> {
            if (existingWindow == null || (currentTime - existingWindow.getWindowStartTime()) > WINDOW_SIZE_MS) {
                // Create a new window if none exists or the previous window has expired
                return new RateLimitWindow(currentTime, 1);
            } else {
                // Increment count within the current active window
                existingWindow.increment();
                return existingWindow;
            }
        });

        // Check if user has exceeded their tier limits
        return window.getRequestCount() <= tier.getLimitPerMinute();
    }
}
