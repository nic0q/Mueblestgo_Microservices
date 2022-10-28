package com.tingeso.justificative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JustificativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustificativeApplication.class, args);
	}

}
