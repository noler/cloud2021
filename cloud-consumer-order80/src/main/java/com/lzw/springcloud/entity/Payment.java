package com.lzw.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    /**
     * Id
     */
    private Long id ;
    /**
     * 序列号
     */
    private String serial;
}
