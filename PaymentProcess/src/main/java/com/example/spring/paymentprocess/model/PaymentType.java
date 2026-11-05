package com.example.spring.paymentprocess.model;

public class PaymentType {
    String type;
    double amount;

    public PaymentType() {}

    public PaymentType(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
