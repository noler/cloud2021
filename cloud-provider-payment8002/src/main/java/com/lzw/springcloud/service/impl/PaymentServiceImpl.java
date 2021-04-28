package com.lzw.springcloud.service.impl;

import com.lzw.springcloud.dao.PaymentDao;
import com.lzw.springcloud.entity.Payment;
import com.lzw.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    PaymentDao payMentDao;

    public int savePayMent(Payment payMent){
       return payMentDao.add(payMent);
    }

    public Payment selectById(Long id){
        return payMentDao.selectById(id);
    }
}
