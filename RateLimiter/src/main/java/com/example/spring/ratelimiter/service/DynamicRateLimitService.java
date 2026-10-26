package com.example.spring.ratelimiter.service;

import com.example.spring.ratelimiter.model.UserTier;

import java.util.concurrent.atomic.AtomicInteger;

public class DynamicRateLimitService {
    // Global constraints
    private static final int GLOBAL_LIMIT = 80;

    // Guaranteed minimum per window (5 seconds)
    private static final int ENTERPRISE_GUARANTEE = 30;
    private static final int PREMIUM_GUARANTEE = 10;
    private static final int FREE_GUARANTEE = 5;

    // Trackers for the current 5-second window
    private final AtomicInteger globalCounter = new AtomicInteger(0);
    private final AtomicInteger enterpriseCounter = new AtomicInteger(0);
    private final AtomicInteger premimumCounter = new AtomicInteger(0);
    private final AtomicInteger freeCounter = new AtomicInteger(0);

    // Window tracking timestamp
    private volatile long windowStartTimestamp = System.currentTimeMillis();
    private static final long WINDOW_SIZE_MS = 5000; // 5 seconds

    public synchronized boolean allowRequests(UserTier tier) {
        long currentTime = System.currentTimeMillis();

        // 1. Reset window counters if 5 seconds have passed
        if (currentTime - windowStartTimestamp > WINDOW_SIZE_MS) {
            windowStartTimestamp = currentTime;
            globalCounter.set(0);
            enterpriseCounter.set(0);
            premimumCounter.set(0);
            freeCounter.set(0);
        }

        // 2. Enforce global safety ceiling (Never exceed downstream capacity of 80)
        if (globalCounter.get() >= GLOBAL_LIMIT) {
            return false;
        }

        // 3. Tier specific dynamic evaluation
        boolean allowed = switch (tier) {
            case ENTERPRISE -> {
                // Enterprise gets the guaranteed, or can consume from the global pool up to 80
                yield enterpriseCounter.incrementAndGet() > 0; // Enterprise always prioritized within global
                // limit
            }
            case PREMIUM ->  {
                // Premium gets its guaranteed plus can use shared pool if Enterprise hasn't maxed
                int currentPrem = premimumCounter.incrementAndGet();
                if (currentPrem <= PREMIUM_GUARANTEE) {
                    yield true;
                } else {
                    // Check if global limit has room for them to borrow
                    yield globalCounter.get() < GLOBAL_LIMIT;
                }
            }
            case FREE -> {
                // Free strictly capped at their guarantee OR only if plenty of global headroom
                int currentFree = freeCounter.incrementAndGet();
                if (currentFree <= FREE_GUARANTEE) {
                    yield true;
                } else {
                    // free users can only borrow if global utilization is low (e.g under 60 seconds)
                    yield globalCounter.get() < 60;
                }
            }
        };

        if (allowed) {
            globalCounter.incrementAndGet();
        }

        return allowed;
    }

}
