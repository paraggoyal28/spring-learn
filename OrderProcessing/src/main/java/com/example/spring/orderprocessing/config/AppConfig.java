package com.example.spring.orderprocessing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Java based configuration class
public class AppConfig {
    @Bean
    public String appVersion() {
        return "v1.0.0-ENTERPRISE";
    }
}
