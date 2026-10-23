package com.learn.spring.basic_hiberante_app.service;

import com.learn.spring.basic_hiberante_app.service.impl.Animal;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("cat")
public class Cat implements Animal {
    @Override
    public String sound() {
        return "meow";
    }
}
