package com.tingeso.salarie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SalarieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalarieApplication.class, args);
	}

}
