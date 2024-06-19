package com.example.learningspringboot;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@Scope("session")
@RequestMapping(value = "/api/")
public class TestController1 {
	
	@Autowired
	User user;

//	@Autowired
//	Student student;
	
	public TestController1() {
		System.out.println("TestController1 instance intialization");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("TestController1 object hashCode: " + this.hashCode() 
			+ " User object hashCode: " + user.hashCode());
	}
	
	@GetMapping(path="/fetchUser")
	public ResponseEntity<String> getUserDetails() {
		System.out.println("fetchUser api invoked");
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	@GetMapping(path="/logout")
	public ResponseEntity<String> getUserDetails(HttpServletRequest request) {
		System.out.println("end the session");
		HttpSession session = request.getSession();
		session.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
}
