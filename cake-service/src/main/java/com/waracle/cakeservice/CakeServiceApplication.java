package com.waracle.cakeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CakeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CakeServiceApplication.class, args);
    }
}
