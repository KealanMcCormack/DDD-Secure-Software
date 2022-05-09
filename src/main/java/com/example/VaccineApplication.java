package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootConfiguration
public class VaccineApplication {
    public static void main (String[] args){
        SpringApplication.run(VaccineApplication.class,args);
    }
}

