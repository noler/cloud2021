package com.lzw.springcloud.service.failback;

import com.lzw.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceFailBack implements PaymentFeignService {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "PaymentFeignServiceFailBack:服务端不可用，请稍后再试。/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFeignServiceFailBack:服务端不可用，请稍后再试。/(ㄒoㄒ)/~~";
    }
}
