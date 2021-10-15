package com.example.bvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BeverageVendingMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeverageVendingMachineApplication.class, args);
	}

}
