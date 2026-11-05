package com.example.spring.paymentprocess.model;

import org.springframework.stereotype.Component;

@Component
public class CashPayment implements Payment {

    @Override
    public void doPayment(double amount) {
        System.out.println("Paying via cash " + amount);
    }

    @Override
    public String getPaymentType() {
        return "CASH";
    }
}
