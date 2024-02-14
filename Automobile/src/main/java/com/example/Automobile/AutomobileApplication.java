package com.example.Automobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AutomobileApplication {

	public static void main(String[] args) {

		SpringApplication.run(AutomobileApplication.class, args);
	}
}
