package com.swiftavenue.vanhacks2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Vanhacks2018Application {
	public static void main(String[] args) {
		SpringApplication.run(Vanhacks2018Application.class, args);
	}
}
