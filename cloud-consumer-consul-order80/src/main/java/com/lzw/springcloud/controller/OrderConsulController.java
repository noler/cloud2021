package com.lzw.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderConsulController {
    public static final String INVOKE_URL="http://cloud-provider-consul-payment";
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/payment/consul")
    public String getPaymentConsul(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }
}
