package com.cafe24.springex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@ComponentScan("com.cafe24.springex.controller")
@EnableAutoConfiguration
@SpringBootConfiguration*/
@SpringBootApplication
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class);
	}
}
