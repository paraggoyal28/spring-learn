package com.learn.spring.basic_hiberante_app;

import com.learn.spring.basic_hiberante_app.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicHiberanteAppApplication {


	public static void main(String[] args) {

		SpringApplication.run(BasicHiberanteAppApplication.class, args);
	}

}
