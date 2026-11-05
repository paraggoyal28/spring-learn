package com.example.spring.paymentprocess.model;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements Payment {
    @Override
    public void doPayment(double amount) {
        System.out.println("Processing credit card payment..." + amount);
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }
}
