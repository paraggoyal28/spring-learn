package com.example.spring.paymentprocess.model;

public interface Payment {
    void doPayment(double amount);
    String getPaymentType();
}
