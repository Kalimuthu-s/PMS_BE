package com.sathish.angulartest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sathish.angulartest.gowtham")
public class AngulartestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngulartestApplication.class, args);
	}

}
