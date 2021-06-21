package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import com.lzw.springcloud.lib.LoadBalanced;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@DefaultProperties(defaultFallback="payment_Global_FallbackMethod")
public class OrderController {
    private static final String PAYMENT_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalanced loadBalanced;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/consumer/get/port")
    public String getPort(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance instance = loadBalanced.instances(instances);
        return restTemplate.getForObject(instance.getUri()+"/get/port",String.class);
    }


    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){

        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/hystrix/ok/" + id, String.class);
        log.info("*******result:"+result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallBack",
    commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/hystrix/timeout/" + id, String.class);
        log.info("*******result:"+result);
        return result;
    }

    public String paymentInfo_TimeOutFallBack(@PathVariable("id") Integer id){

        return "80端 请求服务端繁忙请稍后再试或自身逻辑错误😭";
    }

    public String payment_Global_FallbackMethod(){
        return "Global_FallbackMethod: 服务繁忙请稍后再试";
    }
}



