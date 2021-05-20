package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class OrderController {
    private static final String PAYMENT_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/save")
    public CommentResult savePayment(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/save",payment,CommentResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> selectById(@PathVariable("id") Long id){
        return  restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommentResult.class);
    }
    @GetMapping("/consumer/entity/payment/get/{id}")
    public CommentResult<Payment> selectEntityById(@PathVariable("id")Long id){
        ResponseEntity<CommentResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommentResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommentResult(444,"操作失败！");
        }

    }
}



