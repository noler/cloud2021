package com.lzw.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service

public class PaymentService {

    public String paymentInfo_OK(Integer id){
        int i =10/id;
        return "线程池： "+Thread.currentThread().getName() +"paymentInfo_Ok,id: "+id+"\t 😄";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallBack",
    commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName() +"paymentInfo_TimeOut,id: "+id+"\t 😄";
    }

    public String paymentInfo_TimeOutFallBack(Integer id){
        return "8001服务端paymentInfo_TimeOut出错:请稍后再试或联系系统管理员/(ㄒoㄒ)/~~";
    }


}
