package com.lzw.springcloud.service.impl;

import com.lzw.springcloud.service.IZabbixService;
import com.lzw.springcloud.utils.ZabbixConnection75;
import com.lzw.springcloud.utils.ZabbixConnection96;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class ZabbixServiceImpl  implements IZabbixService {
    private ZabbixApi api75 = ZabbixConnection75.connectZabbix();
    private ZabbixApi api96 = ZabbixConnection96.connectZabbix();


    @Override
    public Map<String,Object> call75(String method, Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {

            RequestBuilder requestBuilder = RequestBuilder.newBuilder().method(method);
            if(params!=null){
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    requestBuilder.paramEntry(entry.getKey(), entry.getValue());
                }
//                requestBuilder.paramEntry("auth",getToken());
            }
            Request getRequest = requestBuilder.build();
            result= api75.call(getRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Object> call96(String method, Map<String, Object> params) {
        Map<String,Object> result = new HashMap<>();
        try {

            RequestBuilder requestBuilder = RequestBuilder.newBuilder().method(method);
            if(params!=null){
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    requestBuilder.paramEntry(entry.getKey(), entry.getValue());
                }
//                requestBuilder.paramEntry("auth",getToken());
            }
            Request getRequest = requestBuilder.build();
            result= api96.call(getRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
