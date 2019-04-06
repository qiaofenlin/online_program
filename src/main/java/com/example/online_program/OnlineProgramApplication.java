package com.example.online_program;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@RabbitListener //开启rabbitmq 监听
@SpringBootApplication
@EnableCaching
public class OnlineProgramApplication extends SpringBootServletInitializer {


    protected SpringApplicationBuilder builder() {
        return builder().sources(OnlineProgramApplication.class);
    }

    public static void main(String[] args) {
        //解决netty冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(OnlineProgramApplication.class, args);
    }
}


