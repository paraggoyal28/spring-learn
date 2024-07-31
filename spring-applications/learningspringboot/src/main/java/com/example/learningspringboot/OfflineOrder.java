package com.example.learningspringboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix="order", value="isOnline", havingValue = "false", matchIfMissing = false)
public class OfflineOrder{
    public OfflineOrder() {
        System.out.println("Offline Order Initialized");
    }
}
