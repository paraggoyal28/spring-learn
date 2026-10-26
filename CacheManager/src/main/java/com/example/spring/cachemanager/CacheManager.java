package com.example.spring.cachemanager;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CacheManager {

    // 1. Execute automatically after dependencies are injected
    @PostConstruct
    public void initCache() {
        System.out.println("--> [Lifecycle] Bean is created! Initializing system cache.");
        // Imagine loading reference data from the database into memory here
    }

    // Normal business logic
    public void performTask() {
        System.out.println("---> [Task] CacheManager is actively doing work.");
    }

    // 2. Executed automatically right before the application shuts down
    @PreDestroy
    public void clearCache() {
        System.out.println("----> [Lifecycle] Spring container is shutting down! Clearing cache and freeing resources...");
    }
}
