package com.chains.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LibrarySpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySpringbootApplication.class, args);
	}
	@GetMapping
	public String health(){
		return "SUCCESS!";
	}

}
