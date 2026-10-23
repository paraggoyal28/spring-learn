package com.learn.spring.basic_hiberante_app.controller;

import com.learn.spring.basic_hiberante_app.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping
    public ResponseEntity<String> getAnimals() {
        return new ResponseEntity<>(animalService.makeSound(), HttpStatus.OK);
    }
}
