package com.lzw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitMqProvider8801Main {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProvider8801Main.class,args);
    }
}
