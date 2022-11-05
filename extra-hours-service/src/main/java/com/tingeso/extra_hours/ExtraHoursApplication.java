package com.tingeso.extra_hours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExtraHoursApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExtraHoursApplication.class, args);
  }
}
