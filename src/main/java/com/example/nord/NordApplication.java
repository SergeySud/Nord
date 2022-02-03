package com.example.nord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class NordApplication {

    public static void main(String[] args) {
        SpringApplication.run(NordApplication.class, args);
    }

}
