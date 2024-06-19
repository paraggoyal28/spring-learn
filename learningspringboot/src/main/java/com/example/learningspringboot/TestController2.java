package com.example.learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/api/")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestController2 {
	@Autowired
	User user;
	
	public TestController2() {
		System.out.println("TestController2 instance intialization");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("TestController2 object hashCode: " + this.hashCode() 
			+ " User object hashCode: " + user.hashCode());
	}
	
	@GetMapping(path="/fetchUser2")
	public ResponseEntity<String> getUserDetails() {
		System.out.println("fetchUser2 api invoked");
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
