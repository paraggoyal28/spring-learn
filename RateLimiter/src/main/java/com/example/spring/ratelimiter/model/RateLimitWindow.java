package com.example.spring.ratelimiter.model;

public class RateLimitWindow {
    private final long windowStartTime;
    private int requestCount;

    public RateLimitWindow(long windowStartTime, int requestCount) {
        this.windowStartTime = windowStartTime;
        this.requestCount = requestCount;
    }

    public long getWindowStartTime() {
        return windowStartTime;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void increment() {
        this.requestCount++;
    }
}
