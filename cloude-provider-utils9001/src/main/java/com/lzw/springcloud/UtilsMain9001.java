package com.lzw.springcloud;

import cn.hutool.extra.spring.SpringUtil;
import com.lzw.springcloud.service.ExcelService;
import com.lzw.springcloud.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lzw.springcloud.mapper")
public class UtilsMain9001 {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(UtilsMain9001.class,args);
        ApplicationContext applicationContext = SpringContextUtils.getApplicationContext();
        ExcelService excelService = (ExcelService) applicationContext.getBean("excelService");
        excelService.wordExcel();
    }
}
