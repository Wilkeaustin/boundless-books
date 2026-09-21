package com.boundlessbooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Starts the Boundless Books Spring Boot application.
 */
@SpringBootApplication
public class BoundlessBooksApplication {
	/**
	 * Runs the application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BoundlessBooksApplication.class, args);
	}
}
