package com.lzw.springcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderZkController {
    public static final String INVOKE_URL="http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;


    @RequestMapping("/consumer/payment/zk")
    public String getPaymentzk(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    }
}
