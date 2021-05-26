package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import com.lzw.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;


    @PostMapping("/consumer/payment/save")
    public CommentResult savePayment(Payment payment){
        return paymentFeignService.savePayment(payment);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> selectById(@PathVariable("id") Long id){
        return paymentFeignService.selectById(id);
    }

    @GetMapping("/consumer/get/port")
    public String getPort(){
        return paymentFeignService.getPort();
    }

}
