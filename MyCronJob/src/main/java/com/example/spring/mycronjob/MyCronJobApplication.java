package com.example.spring.mycronjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyCronJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCronJobApplication.class, args);
    }

}
