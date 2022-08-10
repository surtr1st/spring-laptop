package com.ps17931;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringLaptopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLaptopApplication.class, args);
	}

}
