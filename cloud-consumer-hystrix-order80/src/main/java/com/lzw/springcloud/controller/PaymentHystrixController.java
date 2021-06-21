package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;

import com.lzw.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {
    @Resource
    private PaymentFeignService paymentFeignService;



    @GetMapping("/consumer/hystrix/ok/{id}")
    @HystrixCommand
    public String paymentInfo_Ok(@PathVariable("id") Integer id){

        String result = paymentFeignService.paymentInfo_Ok(id);
        log.info("*******result:"+result);
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallBack",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
            })
    @GetMapping("/consumer/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentFeignService.paymentInfo_TimeOut(id);
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
