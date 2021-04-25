package com.suraj.beermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class BeerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerMicroserviceApplication.class, args);
    }

}
