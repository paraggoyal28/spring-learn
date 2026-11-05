package com.example.spring.paymentprocess.model;

import java.util.List;

public class PaymentRequest {
    List<PaymentType> paymentTypes;
    double grossAmount;

    public PaymentRequest() {
    }

    public PaymentRequest(List<PaymentType> paymentTypes, double grossAmount) {
        this.paymentTypes = paymentTypes;
        this.grossAmount = grossAmount;
    }

    public List<PaymentType> getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(List<PaymentType> paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "paymentTypes=" + paymentTypes +
                ", grossAmount=" + grossAmount +
                '}';
    }
}
