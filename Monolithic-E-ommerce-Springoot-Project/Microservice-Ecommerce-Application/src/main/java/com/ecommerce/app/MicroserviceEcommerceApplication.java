package com.ecommerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ecommerce.app")
public class MicroserviceEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEcommerceApplication.class, args);
	}

}
