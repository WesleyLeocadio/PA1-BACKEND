package com.pa1.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
