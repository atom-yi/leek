package com.cs314.leek.base.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@EnableSwagger2
@EnableOpenApi
public class LeekBaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeekBaseServiceApplication.class, args);
    }
}
