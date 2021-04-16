package com.lzw.springcloud.controller;

import com.lzw.springcloud.entity.CommentResult;
import com.lzw.springcloud.entity.Payment;
import com.lzw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @PostMapping("/save")
    public CommentResult savePayment(Payment payment){
        int result = paymentService.savePayMent(payment);
        if(result>0){
            log.info("****插入结果:"+result);
            return new CommentResult(200,"插入数据库成功",result);
        }else{
            log.info("****插入数据库失败:"+payment);

            return new CommentResult(404,"插入数据库失败");
        }
    }
    @GetMapping("/get/{id}")
    public CommentResult<Payment> selectById(@PathVariable("id") Long id){
        Payment payment = paymentService.selectById(id);
        if(payment == null){
            return new CommentResult<>(404,"查询失败，查询id:"+id);
        }else{
            return new CommentResult<>(202,"查询成功", payment);
        }
    }
}
