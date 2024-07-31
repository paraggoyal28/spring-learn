package com.example.learningspringboot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class MySQLConnection {

    @Value("${user}")
    String user;

    @Value("${password}")
    String password;

    @PostConstruct
    public void init() {
        System.out.println("username: " + user + " password: " + password);
    }
}
