package com.api.movie;

import com.api.movie.exceptions.ResourceNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MovieApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Prompt user for password
		System.out.print("Enter password: ");
		String inputPassword = scanner.nextLine();

		// Validate password
		if ("123".equals(inputPassword)) {
			SpringApplication.run(MovieApplication.class, args);
		} else {
			throw new ResourceNotFoundException("Not admin - access denied.");
		}

		scanner.close();
	}
}
