package com.example.spring.ratelimiter.model;

public enum UserTier {
    FREE(5), //  5 requests per minute,
    PREMIUM(60), // 60 requests per minute,
    ENTERPRISE(1000); // 1000 requests per minute

    private final int limitPerMinute;

    UserTier(int limitPerMinute) {
        this.limitPerMinute = limitPerMinute;
    }

    public int getLimitPerMinute() {
        return limitPerMinute;
    }
}



