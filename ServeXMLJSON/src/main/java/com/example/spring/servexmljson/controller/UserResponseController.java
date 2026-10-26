package com.example.spring.servexmljson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserResponseController {

    // same method serves both JSON and XML
    @GetMapping(path="/{id}", produces={"application/json", "application/xml"})
    public UserResponse getUser(@PathVariable Long id) {
        return new UserResponse(id, "Alice Smith", "alice@example.com");
    }

    // Data Transfer Objects (Record)
    public record UserResponse(Long id, String name, String email) {}
}
