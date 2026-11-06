package com.example.spring.orderprocessing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BeanA {
    @Lazy
    @Autowired
    BeanB beanB;
}
