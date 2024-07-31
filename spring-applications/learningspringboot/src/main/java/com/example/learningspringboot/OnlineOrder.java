package com.example.learningspringboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix="order", value="isOnline", havingValue = "true", matchIfMissing = false)
public class OnlineOrder {
    public OnlineOrder() {
        System.out.println("Online Order Initialized");
    }
}
