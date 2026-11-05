package com.example.spring.paymentprocess.service;

import com.example.spring.paymentprocess.model.Payment;
import com.example.spring.paymentprocess.model.PaymentRequest;
import com.example.spring.paymentprocess.model.PaymentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentProcessor {
    private static final Logger log = LoggerFactory.getLogger(PaymentProcessor.class);

    private final Map<String, Payment> paymentRegistry = new HashMap<>();

    public PaymentProcessor(List<Payment> payments) {
        for (Payment payment : payments) {
            paymentRegistry.put(payment.getPaymentType(), payment);
        }
    }

    public void doPayment(PaymentRequest paymentRequest) {
        log.info("Payment Request: " + paymentRequest.toString());

        for (PaymentType paymentType : paymentRequest.getPaymentTypes()) {
            Payment paymentService = paymentRegistry.get(paymentType.getType());

            if (paymentService == null) {
                throw new IllegalArgumentException("Payment Type Not Found");
            }

            paymentService.doPayment(paymentType.getAmount());
        }
    }
}
