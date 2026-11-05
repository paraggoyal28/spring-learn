package com.example.spring.paymentprocess.controller;

import com.example.spring.paymentprocess.model.Payment;
import com.example.spring.paymentprocess.model.PaymentRequest;
import com.example.spring.paymentprocess.service.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentProcessor paymentProcessor;

    @GetMapping("/pay")
    public void makePayment(@RequestBody PaymentRequest paymentRequest) {
        paymentProcessor.doPayment(paymentRequest);
    }
}
