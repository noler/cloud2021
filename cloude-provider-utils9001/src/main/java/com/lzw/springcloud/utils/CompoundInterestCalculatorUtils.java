package com.lzw.springcloud.utils;


/**
 *复利计算工具类
 */
public class CompoundInterestCalculatorUtils {
    //本金
    private Double selfMony;
    //每月存入金额
    private Double monthMony;
    //年限
    private Integer n;
    //终值
    private Double sumMony;
    //利率
    private Double rate;

    public void MonthCalculator(Double monthMony,Integer n,Double rate){
        if(n<1){
            return;
        }
        //计算 本金、终值
        sumMony = 0D;
        sumMony+=monthMony*(1+rate);
        for(int i=0;i<n;i++){
            sumMony+=monthMony*(1+rate);
        }
    }
    public void SumCaculator(){

    }
}
