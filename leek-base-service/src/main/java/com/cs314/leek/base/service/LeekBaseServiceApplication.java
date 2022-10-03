package com.cs314.leek.base.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class LeekBaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeekBaseServiceApplication.class, args);
    }
}
