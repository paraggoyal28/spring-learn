package com.example.spring.orderprocessing.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShoppingCart {

    public ShoppingCart() {
        System.out.println("-> ShoppingCart Prototype Instance Created!");
    }

    @PostConstruct
    public void init() {
        System.out.println("-> ShoppingCart initialized for a specific session.");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("-> ShoppingCart destroyed for a specific session.");
    }
}
