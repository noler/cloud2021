package com.lzw.springcloud.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface OracleMapper {
    List<Map<String,Object>> getApplicationDisk();
    List<Map<String,Object>>  getDatabaseDisk();
    List<Map<String,Object>>  getApplicationMemory();
    List<Map<String,Object>>  getDatabaseMemory();
    List<Map<String,Object>>  getApplicationCpu();
    List<Map<String,Object>>  getDatabaseCpu();

    List<Map<String,Object>> getApplicationCpuTOP3();
    List<Map<String,Object>> getDatabaseCpuTOP3();
    List<Map<String,Object>> getApplicationMemoryTOP3();
    List<Map<String,Object>> getDatabaseMemoryTOP3();
    List<Map<String,Object>> getApplicationDiskTOP3();
    List<Map<String,Object>> getDatabaseDiskTOP3();

    List<Map<String,Object>> getJifeiCpu();
    List<Map<String,Object>> getJifeiMemory();
    List<Map<String,Object>> getJifeiDisk();
    List<Map<String,Object>> getCrmCpu();
    List<Map<String,Object>> getCrmMemory();
    List<Map<String,Object>> getCrmDisk();

    List<Map<String,Object>> getJifeiCpuTop3();
    List<Map<String,Object>> getJifeiMemoryTop3();
    List<Map<String,Object>> getCrmCpuTop3();
    List<Map<String,Object>> getCrmMemoryTop3();

}
