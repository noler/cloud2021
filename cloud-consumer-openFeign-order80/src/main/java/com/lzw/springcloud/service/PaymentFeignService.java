package com.lzw.springcloud.service;


import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value="cloud-payment-service")
public interface PaymentFeignService {

    @PostMapping("/payment/save")
    public CommentResult savePayment(Payment payment);

    @GetMapping("/payment/get/{id}")
    public CommentResult<Payment> selectById(@PathVariable("id") Long id);

    @GetMapping("/get/port")
    public String getPort();
}
