package com.lzw.springcloud.service;

import java.util.Map;

public interface IZabbixService {
    Map<String,Object> call75(String method, Map<String, Object> params);
    Map<String,Object> call96(String method, Map<String, Object> params);
    Map<String,Object> call98(String method,Map<String,Object> params);
}
