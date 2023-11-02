package com.example.ingeneo_technical_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class IngeneoTechnicalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngeneoTechnicalTestApplication.class, args);
	}
}
