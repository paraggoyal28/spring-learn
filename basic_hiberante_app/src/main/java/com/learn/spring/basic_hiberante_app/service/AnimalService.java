package com.learn.spring.basic_hiberante_app.service;

import com.learn.spring.basic_hiberante_app.service.impl.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    @Autowired
    @Qualifier("dog")
    Animal animal;

    public String makeSound() {
        return animal.sound();
    }
}
