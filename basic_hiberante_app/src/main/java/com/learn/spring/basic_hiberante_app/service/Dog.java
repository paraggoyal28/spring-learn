package com.learn.spring.basic_hiberante_app.service;

import com.learn.spring.basic_hiberante_app.service.impl.Animal;
import org.springframework.stereotype.Component;

@Component("dog")
public class Dog implements Animal {
    @Override
    public String sound() {
        return "bark";
    }
}
