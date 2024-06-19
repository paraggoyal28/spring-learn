package com.example.learningspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Component
public class User {

	@Autowired(required=false)
	OfflineOrder offlineOrder;

	@Autowired(required=false)
	OnlineOrder onlineOrder;

	
	public User() {
		System.out.println("User initialization");
	}
	
	@PostConstruct
	public void init() {

		System.out.println("User object hashcode: " + this.hashCode());
		System.out.println("is onlineOrder object null: " + Objects.isNull(onlineOrder));
		System.out.println("is offlineOrder object null: " + Objects.isNull(offlineOrder));
	}
}
