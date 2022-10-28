package com.tingeso.marcasreloj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MarcasRelojApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcasRelojApplication.class, args);
	}

}
