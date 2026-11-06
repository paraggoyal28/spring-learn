package com.example.spring.orderprocessing.service.impl;

import com.example.spring.orderprocessing.service.DiscountService;
import org.springframework.stereotype.Service;

@Service("vipDiscount") // Named Components
public class VipDiscountService implements DiscountService {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.8;
    }
}
