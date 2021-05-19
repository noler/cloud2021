package com.lzw.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UtilsMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(UtilsMain9001.class,args);
    }
}
