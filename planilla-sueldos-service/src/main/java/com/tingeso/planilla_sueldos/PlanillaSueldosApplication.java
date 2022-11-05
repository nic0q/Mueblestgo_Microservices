package com.tingeso.planilla_sueldos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlanillaSueldosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanillaSueldosApplication.class, args);
	}

}
