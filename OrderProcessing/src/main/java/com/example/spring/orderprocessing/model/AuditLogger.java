package com.example.spring.orderprocessing.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component

public class AuditLogger {

    public AuditLogger() {
        System.out.println("-> [Lifecycle 1] AuditLogger constructor called");
    }

    @PostConstruct // Concept 4: Init
    public void init() {
        System.out.println("-> [Lifecycle 2] AuditLogger @PostConstruct: Initializing log stream...");
    }

    public void log(String message) {
        System.out.println("[AUDIT LOG]: " + message);
    }

    @PreDestroy // Concept 4: Destroy bean
    public void cleanup() {
        System.out.println("-> [Lifecycle 4]: AuditLogger @PreDestroy Flushing log streams before shutdown...");
    }
}
