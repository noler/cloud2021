package com.lzw.springcloud.dao;

import com.lzw.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int add(Payment payMent);

    Payment selectById(@Param("id")Long id);
}
