package com.example.spring.paymentprocess.model;

import org.springframework.stereotype.Component;

@Component
public class PaypalPayment implements Payment {

    @Override
    public void doPayment(double amount) {
    System.out.println("Processing PayPal payment...");
    }

    @Override
    public String getPaymentType() {
        return "PAYPAL";
    }
}
