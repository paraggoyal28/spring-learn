package com.example.spring.paymentprocess.model;

import org.springframework.stereotype.Component;

@Component
public class GPayPayment implements Payment {

    @Override
    public void doPayment(double amount) {
        System.out.println("Paying via Gpay " + amount);
    }

    @Override
    public String getPaymentType() {
        return "GPAY";
    }
}
