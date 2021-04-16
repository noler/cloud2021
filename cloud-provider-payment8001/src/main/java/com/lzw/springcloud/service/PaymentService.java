package com.lzw.springcloud.service;

import com.lzw.springcloud.entity.Payment;

public interface PaymentService {

    int savePayMent(Payment payMent);

    Payment selectById(Long id);
}
