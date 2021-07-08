package com.lzw.springcloud.service;

import com.lzw.springcloud.mapper.OracleMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {
    @Resource
    private OracleMapper oracleMapper;
    @Value("${outfile.path}")
    private  String outPath = "F:/项目资料/电信号码平台/月报/7月份";
    private  List<Row> rowList = new ArrayList<>();

    public  void wordExcel() throws IOException {
        //创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        //创建第一个sheet数据
        createSheet4CoreData(workbook);

        //创建第二个sheet页
        createSheetCrmAndJData(workbook);
        //创建第三个sheet页
        createSheetCrmAndJDataTop3(workbook);
        //创建第四个sheet页
        createSheet4CoreDataTOP3(workbook);

        FileOutputStream fileOutputStream = new FileOutputStream(outPath +"/"+ "IaaS运营月报.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private  void createSheet4CoreDataTOP3(Workbook workbook) {
        //创建表
        Sheet sheet = workbook.createSheet("四大系统核心数据TOP3");
        //创建行
        rowList.clear();
        for(int i=0 ; i<19 ; i++){
            rowList.add(sheet.createRow(i));
        }
        Row row1 = rowList.get(0);
        row1.createCell(0).setCellValue("业务系统");
        row1.createCell(1).setCellValue("服务器类型");
        row1.createCell(2).setCellValue("IP地址TOP3");
        row1.createCell(3).setCellValue("CPU利用率均值");
        row1.createCell(4).setCellValue("IP地址TOP3");
        row1.createCell(5).setCellValue("内存利用率均值");
        //合并单元格
        //综合激活系统
        sheet.addMergedRegion(new CellRangeAddress(1,3,0,0));
        //运营流程系统
        sheet.addMergedRegion(new CellRangeAddress(4,7,0,0));
        //门户系统
        sheet.addMergedRegion(new CellRangeAddress(8,13,0,0));
        //ODS系统
        sheet.addMergedRegion(new CellRangeAddress(14,18,0,0));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(1,3,1,1));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(4,6,1,1));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(8,10,1,1));
        //数据库服务器
        sheet.addMergedRegion(new CellRangeAddress(11,13,1,1));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(14,16,1,1));
        //数据库服务器
        sheet.addMergedRegion(new CellRangeAddress(17,18,1,1));
        //设置表格固定数据
        Row row2 = rowList.get(1);
        row2.createCell(0).setCellValue("综合激活系统");
        row2.createCell(1).setCellValue("应用服务器");
        Row row5 = rowList.get(4);
        row5.createCell(0).setCellValue("运营流程系统");
        row5.createCell(1).setCellValue("应用服务器");
        Row row7 = rowList.get(7);
        row7.createCell(1).setCellValue("数据库服务器");
        Row row9 =rowList.get(8);
        row9.createCell(0).setCellValue("门户系统");
        row9.createCell(1).setCellValue("应用服务器");
        Row row12 = rowList.get(11);
        row12.createCell(1).setCellValue("数据库服务器");
        Row row15 = rowList.get(14);
        row15.createCell(0).setCellValue("ODS系统");
        row15.createCell(1).setCellValue("应用服务器");
        Row row18 = rowList.get(17);
        row18.createCell(1).setCellValue("数据库服务器");
        List<Map<String, Object>> applicationCpuTOP3 = oracleMapper.getApplicationCpuTOP3();
        List<Map<String, Object>> databaseCpuTOP3 = oracleMapper.getDatabaseCpuTOP3();
        List<Map<String, Object>> applicationMemoryTOP3 = oracleMapper.getApplicationMemoryTOP3();
        List<Map<String, Object>> databaseMemoryTOP3 = oracleMapper.getDatabaseMemoryTOP3();
        List<Map<String, Object>> applicationDisk = oracleMapper.getApplicationDisk();
        List<Map<String, Object>> databaseDisk = oracleMapper.getDatabaseDisk();

    }

    private  void createSheetCrmAndJDataTop3(Workbook workbook) {
        //创建表
        Sheet sheet = workbook.createSheet("天翼云TOP3表格");
        rowList.clear();
        //创建行
        for(int i=0 ; i<47 ; i++){
            rowList.add(sheet.createRow(i));
        }


        Row row1 = rowList.get(0);
        row1.createCell(0).setCellValue("系统域");
        row1.createCell(1).setCellValue("部署模块");
        row1.createCell(2).setCellValue("CPU利用率均值IP地址TOP3");
        row1.createCell(3).setCellValue("CPU利用率均值");
        row1.createCell(4).setCellValue("内存利用率均值IP地址TOP3");
        row1.createCell(5).setCellValue("内存利用率均值");
        Row row21 = rowList.get(20);
        row21.createCell(0).setCellValue("系统域");
        row21.createCell(1).setCellValue("部署模块");
        row21.createCell(2).setCellValue("CPU利用率均值IP地址TOP3");
        row21.createCell(3).setCellValue("CPU利用率均值");
        row21.createCell(4).setCellValue("内存利用率均值IP地址TOP3");
        row21.createCell(5).setCellValue("内存利用率均值");
        Row row44 = rowList.get(43);
        row44.createCell(0).setCellValue("系统域");
        row44.createCell(1).setCellValue("部署模块");
        row44.createCell(2).setCellValue("高负荷ip");
        row44.createCell(3).setCellValue("高负荷时长");
        //合并单元格
        //CRM
        sheet.addMergedRegion(new CellRangeAddress(1,18,0,0));
        //TELEDB
        sheet.addMergedRegion(new CellRangeAddress(1,3,1,1));
        //MQ
        sheet.addMergedRegion(new CellRangeAddress(4,6,1,1));
        //缓存
        sheet.addMergedRegion(new CellRangeAddress(7,9,1,1));
        //ZK
        sheet.addMergedRegion(new CellRangeAddress(10,12,1,1));
        //BACKUP
        sheet.addMergedRegion(new CellRangeAddress(13,15,1,1));
        //CRM应用
        sheet.addMergedRegion(new CellRangeAddress(16,18,1,1));
        //计费
        sheet.addMergedRegion(new CellRangeAddress(21,41,0,0));
        //TELEDB
        sheet.addMergedRegion(new CellRangeAddress(21,23,1,1));
        //MQ
        sheet.addMergedRegion(new CellRangeAddress(24,26,1,1));
        //缓存
        sheet.addMergedRegion(new CellRangeAddress(27,29,1,1));
        //ZK
        sheet.addMergedRegion(new CellRangeAddress(30,32,1,1));
        //BACKUP
        sheet.addMergedRegion(new CellRangeAddress(33,35,1,1));
        //DFS
        sheet.addMergedRegion(new CellRangeAddress(36,38,1,1));
        //计费应用
        sheet.addMergedRegion(new CellRangeAddress(39,41,1,1));

        //计费高负荷
        sheet.addMergedRegion(new CellRangeAddress(44,46,0,0));
        sheet.addMergedRegion(new CellRangeAddress(44,46,1,1));

        Row row2 = rowList.get(1);
        row2.createCell(0).setCellValue("CRM");
        row2.createCell(1).setCellValue("BACKUP");
        Row row5 = rowList.get(4);
        row5.createCell(1).setCellValue("crm应用");
        rowList.get(7).createCell(1).setCellValue("MQ");
        rowList.get(10).createCell(1).setCellValue("teledb");
        rowList.get(13).createCell(1).setCellValue("ZK");
        rowList.get(16).createCell(1).setCellValue("缓存");

        rowList.get(21).createCell(0).setCellValue("计费");
        rowList.get(21).createCell(1).setCellValue("BACKUP");
        rowList.get(24).createCell(1).setCellValue("DFS");
        rowList.get(27).createCell(1).setCellValue("MQ");
        rowList.get(30).createCell(1).setCellValue("TELEDB");
        rowList.get(33).createCell(1).setCellValue("ZK");
        rowList.get(36).createCell(1).setCellValue("缓存");
        rowList.get(39).createCell(1).setCellValue("计费应用");
        rowList.get(44).createCell(0).setCellValue("计费");
        rowList.get(44).createCell(1).setCellValue("计费应用");
    }

    private  void createSheetCrmAndJData(Workbook workbook) {
        //创建第二个sheet页
        //创建表
        Sheet sheet = workbook.createSheet("天翼云作图");
        rowList.clear();
        //创建行
        for(int i=0 ; i<42 ; i++){
            rowList.add(sheet.createRow(i));
        }

        Row row1 = rowList.get(0);
        row1.createCell(0).setCellValue("系统域");
        row1.createCell(1).setCellValue("部署模块");
        row1.createCell(2).setCellValue("指标");
        row1.createCell(3).setCellValue("均值利用率");
        row1.createCell(4).setCellValue("峰值利用率");
        row1.createCell(5).setCellValue("峰值利用率");
        Row row21 = rowList.get(20);
        row21.createCell(0).setCellValue("系统域");
        row21.createCell(1).setCellValue("部署模块");
        row21.createCell(2).setCellValue("指标");
        row21.createCell(3).setCellValue("均值利用率");
        row21.createCell(4).setCellValue("峰值利用率");
        row21.createCell(5).setCellValue("峰值利用率");
        //合并单元格
        //CRM
        sheet.addMergedRegion(new CellRangeAddress(1,18,0,0));
        //TELEDB
        sheet.addMergedRegion(new CellRangeAddress(1,3,1,1));
        //MQ
        sheet.addMergedRegion(new CellRangeAddress(4,6,1,1));
        //缓存
        sheet.addMergedRegion(new CellRangeAddress(7,9,1,1));
        //ZK
        sheet.addMergedRegion(new CellRangeAddress(10,12,1,1));
        //BACKUP
        sheet.addMergedRegion(new CellRangeAddress(13,15,1,1));
        //CRM应用
        sheet.addMergedRegion(new CellRangeAddress(16,18,1,1));
        //计费
        sheet.addMergedRegion(new CellRangeAddress(21,41,0,0));
        //TELEDB
        sheet.addMergedRegion(new CellRangeAddress(21,23,1,1));
        //MQ
        sheet.addMergedRegion(new CellRangeAddress(24,26,1,1));
        //缓存
        sheet.addMergedRegion(new CellRangeAddress(27,29,1,1));
        //ZK
        sheet.addMergedRegion(new CellRangeAddress(30,32,1,1));
        //BACKUP
        sheet.addMergedRegion(new CellRangeAddress(33,35,1,1));
        //DFS
        sheet.addMergedRegion(new CellRangeAddress(36,38,1,1));
        //计费应用
        sheet.addMergedRegion(new CellRangeAddress(39,41,1,1));

        Row row2 = rowList.get(1);
        row2.createCell(0).setCellValue("CRM");
        row2.createCell(1).setCellValue("TELEDB");
        Row row5 = rowList.get(4);
        row5.createCell(1).setCellValue("MQ");
        rowList.get(7).createCell(1).setCellValue("缓存");
        rowList.get(10).createCell(1).setCellValue("ZK");
        rowList.get(13).createCell(1).setCellValue("BACKUP");
        rowList.get(16).createCell(1).setCellValue("CRM应用");
        for(int i=1;i<19;i++){
            Cell cellIndex = rowList.get(i).createCell(2);
            switch (i%3){
                case 1:
                    cellIndex.setCellValue("CPU");
                    break;
                case 2:
                    cellIndex.setCellValue("内存");
                    break;
                case 0:
                    cellIndex.setCellValue("磁盘");
                    break;
            }
        }
        rowList.get(21).createCell(0).setCellValue("计费");
        rowList.get(21).createCell(1).setCellValue("TELEDB");
        rowList.get(24).createCell(1).setCellValue("MQ");
        rowList.get(27).createCell(1).setCellValue("缓存");
        rowList.get(30).createCell(1).setCellValue("ZK");
        rowList.get(33).createCell(1).setCellValue("BACKUP");
        rowList.get(36).createCell(1).setCellValue("DFS");
        rowList.get(39).createCell(1).setCellValue("计费应用");
        for(int i=21;i<42;i++){
            Cell cellIndex = rowList.get(i).createCell(2);
            switch (i%3){
                case 0:
                    cellIndex.setCellValue("CPU");
                    break;
                case 1:
                    cellIndex.setCellValue("内存");
                    break;
                case 2:
                    cellIndex.setCellValue("磁盘");
                    break;
            }
        }
    }

    private  void createSheet4CoreData(Workbook workbook) {
        //创建表
        Sheet sheet = workbook.createSheet("四大系统核心数据查询");
        //创建行
        rowList.clear();
        for(int i=0 ; i<22 ; i++){
            rowList.add(sheet.createRow(i));
        }
        Row row1 = rowList.get(0);
        row1.createCell(3).setCellValue("利用率均值");
        row1.createCell(4).setCellValue("利用率峰值-利用率均值");
        row1.createCell(5).setCellValue("利用率峰值");
        //合并单元格
        //综合激活系统
        sheet.addMergedRegion(new CellRangeAddress(1,3,0,0));
        //运营流程系统
        sheet.addMergedRegion(new CellRangeAddress(4,9,0,0));
        //门户系统
        sheet.addMergedRegion(new CellRangeAddress(10,15,0,0));
        //ODS系统
        sheet.addMergedRegion(new CellRangeAddress(16,21,0,0));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(1,3,1,1));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(4,6,1,1));
        //数据库服务器
        sheet.addMergedRegion(new CellRangeAddress(7,9,1,1));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(10,12,1,1));
        //数据库服务器
        sheet.addMergedRegion(new CellRangeAddress(13,15,1,1));
        //应用服务器
        sheet.addMergedRegion(new CellRangeAddress(16,18,1,1));
        //数据库服务器
        sheet.addMergedRegion(new CellRangeAddress(19,21,1,1));
        //设置表格固定数据
        Row row2 = rowList.get(1);
        row2.createCell(0).setCellValue("综合激活系统");
        row2.createCell(1).setCellValue("应用服务器");
        Row row5 = rowList.get(4);
        row5.createCell(0).setCellValue("运营流程系统");
        row5.createCell(1).setCellValue("应用服务器");
        Row row7 = rowList.get(7);
        row7.createCell(1).setCellValue("数据库服务器");
        Row row11 =rowList.get(10);
        row11.createCell(0).setCellValue("门户系统");
        row11.createCell(1).setCellValue("应用服务器");
        Row row14 = rowList.get(13);
        row14.createCell(1).setCellValue("数据库服务器");
        Row row17 = rowList.get(16);
        row17.createCell(0).setCellValue("ODS系统");
        row17.createCell(1).setCellValue("应用服务器");
        Row row20 = rowList.get(19);
        row20.createCell(1).setCellValue("数据库服务器");
        for(int i=1; i<22; i++){
            Cell cell = rowList.get(i).createCell(2);
            switch (i%3){
                case 1:
                    cell.setCellValue("CPU");
                    break;
                case 2:
                    cell.setCellValue("内存");
                    break;
                case 0:
                    cell.setCellValue("磁盘");
                    break;
            }

        }
        List<Map<String, Object>> applicationCpu = oracleMapper.getApplicationCpu();
        List<Map<String, Object>> applicationMemory = oracleMapper.getApplicationMemory();
        List<Map<String, Object>> applicationDisk = oracleMapper.getApplicationDisk();
        List<Map<String, Object>> databaseCpu = oracleMapper.getDatabaseCpu();
        List<Map<String, Object>> databaseMemory = oracleMapper.getDatabaseMemory();
        List<Map<String, Object>> databaseDisk = oracleMapper.getDatabaseDisk();
        setTableData(applicationCpu,10,4,1,16);
        setTableData(applicationMemory,11,5,2,17);
        setTableData(applicationDisk,12,6,3,18);
        setTableData(databaseCpu,13,7,-1,19);
        setTableData(databaseMemory,14,8,-1,20);
        setTableData(databaseDisk,15,9,-1,21);
    }

    private void setTableData(List<Map<String, Object>> applicationCpu,int a,int b,int c,int d) {
        for(Map<String,Object> map : applicationCpu){

            switch (map.get("bussise").toString()){
                case "OA门户系统":
                    Row row11Data = rowList.get(a);
                    Double cpuAvg = new Double(map.get("valueAvg").toString());
                    Double cpuMax = new Double(map.get("valueMax").toString());
                    row11Data.createCell(3).setCellValue(cpuAvg);
                    row11Data.createCell(4).setCellValue(cpuMax-cpuAvg);
                    row11Data.createCell(5).setCellValue(cpuMax);
                    break;
                case "运营流程支撑(替换PF/电子运维)":
                    Row row5Data = rowList.get(b);
                    Double row5DataCpuAvg = new Double(map.get("valueAvg").toString());
                    Double row5DataCpuMax = new Double(map.get("valueMax").toString());
                    row5Data.createCell(3).setCellValue(row5DataCpuAvg);
                    row5Data.createCell(4).setCellValue(row5DataCpuMax-row5DataCpuAvg);
                    row5Data.createCell(5).setCellValue(row5DataCpuMax);
                    break;
                case "综合激活_NEW":
                    Row row2Data = rowList.get(c);
                    Double row2DataCpuAvg = new Double(map.get("valueAvg").toString());
                    Double row2DataCpuMax = new Double(map.get("valueMax").toString());
                    row2Data.createCell(3).setCellValue(row2DataCpuAvg);
                    row2Data.createCell(4).setCellValue(row2DataCpuMax-row2DataCpuAvg);
                    row2Data.createCell(5).setCellValue(row2DataCpuMax);
                    break;
                case "ODS":
                    Row row17Data = rowList.get(d);
                    Double row17DataCpuAvg = new Double(map.get("valueAvg").toString());
                    Double row17DataCpuMax = new Double(map.get("valueMax").toString());
                    row17Data.createCell(3).setCellValue(row17DataCpuAvg);
                    row17Data.createCell(4).setCellValue(row17DataCpuMax-row17DataCpuAvg);
                    row17Data.createCell(5).setCellValue(row17DataCpuMax);
                    break;
            }
        }
    }
}
