package com.tingeso.officeRRHH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OfficeRRHHApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfficeRRHHApplication.class, args);
	}

}
