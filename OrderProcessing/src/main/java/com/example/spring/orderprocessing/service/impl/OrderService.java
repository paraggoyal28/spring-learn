package com.example.spring.orderprocessing.service.impl;

import com.example.spring.orderprocessing.model.AuditLogger;
import com.example.spring.orderprocessing.service.DiscountService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // Concept 1: Constructor Injection: (Best practice for mandatory dependencies)
    private final AuditLogger auditLogger;

    @Autowired
    @Qualifier("impDiscount")
    private DiscountService discountService;

    @Autowired
    public OrderService(AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }

    public void processOrder(double amount) {
        double finalAmount = discountService.applyDiscount(amount);
        auditLogger.log("Processed order of original $" + amount + " for final price $" +  finalAmount);
    }
}
