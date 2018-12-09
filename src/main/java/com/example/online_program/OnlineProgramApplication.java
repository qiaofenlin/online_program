package com.example.online_program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineProgramApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder builder() {
        return builder().sources(OnlineProgramApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineProgramApplication.class, args);
    }
}
