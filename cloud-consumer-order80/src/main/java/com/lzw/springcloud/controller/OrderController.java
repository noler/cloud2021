package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    private static final String PAYMENT_URL="http://localhost:8001/payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/save")
    public CommentResult savePayment(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/save",payment,CommentResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> selectById(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/get/"+id,CommentResult.class);
    }
}


