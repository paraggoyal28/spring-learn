package com.example.spring.orderprocessing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BeanB {
    @Autowired
    BeanA beanA;
}
