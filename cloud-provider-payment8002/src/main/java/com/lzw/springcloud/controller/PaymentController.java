package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import com.lzw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/save")
    public CommentResult savePayment(Payment payment){
        int result = paymentService.savePayMent(payment);
        if(result>0){
            log.info("****插入结果:"+result);
            return new CommentResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else{
            log.info("****插入数据库失败:"+payment);

            return new CommentResult(404,"插入数据库失败,serverPort:"+serverPort);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommentResult<Payment> selectById(@PathVariable("id") Long id){
        Payment payment = paymentService.selectById(id);
        if(payment == null){
            return new CommentResult<>(404,"查询失败，查询id:"+id+",serverPort:"+serverPort);
        }else{
            return new CommentResult<>(202,"查询成功,serverPort:"+serverPort, payment);
        }
    }

    @GetMapping("/get/port")
    public String getPort() throws InterruptedException {
        Thread.sleep(3000);
        return serverPort;
    }

}
