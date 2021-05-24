package com.lzw.springcloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzw.springcloud.service.IZabbixService;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ZabbixController {
    private List<String> hostList = new ArrayList<>();
    @Autowired
    private IZabbixService zabbixService;


    public void  findHost(){
        Map<String, Object> params75 = new HashMap<>();
        params75.put("monitored_hosts",true);
        Map<String, Object> repsonse = zabbixService.call75("host.get", params75);
        JSONArray resultArray =(JSONArray) repsonse.get("result");
        for(int i=0;i<resultArray.size();i++){
            hostList.add((String)resultArray.getJSONObject(i).get("hostid"));
        }
        System.out.println(repsonse);
    }

    @RequestMapping("zabbix/import")
    public String exportAndImportZabbixHostConfig() throws DocumentException {
        //查找数据
        findHost();
    //1.拿到主机
    for(String hostItem : hostList) {
        List<String> hostItemList = new ArrayList<>();
        hostItemList.add(hostItem);
        //2.掉api
        //调用zabbix 修改监控 api
        //设置参数
        Map<String, Object> params75 = new HashMap<>();
        Map<String, Object> options75 = new HashMap<>();
        params75.put("options", options75);
        params75.put("format", "xml");
        options75.put("hosts", hostItemList);
        //3.导出
        Map<String, Object> response75 = zabbixService.call75("configuration.export", params75);
        if (response75.containsKey("result")) {
            String xml75 = response75.get("result").toString();
            //4.1改代理
            //4.2改模板
            //4.3根据模板改名字
            Document document = DocumentHelper.parseText(xml75);
            Element root = document.getRootElement();
            Element hostsElement = root.element("hosts");
            Element hostElement = hostsElement.element("host");

            Element proxyElement = hostElement.element("proxy");
            Element nameElement = proxyElement.element("name");
            if (nameElement != null) {
                proxyElement.remove(nameElement);
            }
            List<Element> templates = hostElement.element("templates").elements();
            Integer linuxFlag = 0;
            for (Element template : templates) {
                Element nameElment = template.element("name");
                if (nameElment.getTextTrim().contains("Linux")) {
                    nameElment.setText("Template OS Linux Active UM");
                    linuxFlag = 1;
                    break;
                }
            }
            Element hostName = hostElement.element("name");
            if (linuxFlag == 1) {
                if (!hostName.getTextTrim().contains("_")) {
                    hostName.setText("linux_server_" + hostName.getTextTrim());
                } else {
                    hostName.setText("linux_server_" + hostName.getTextTrim()
                            .substring(hostName.getTextTrim().lastIndexOf("_")));
                }
            }
            xml75 = document.asXML();
            //4.导入
            Map<String, Object> params96 = new HashMap<>();
            Map<String, Object> rules96 = new HashMap<>();
            params96.put("rules", rules96);
            params96.put("format", "xml");
            params96.put("source", xml75);


            Map<String, Object> applications = new HashMap<>();
            applications.put("createMissing", true);
            applications.put("deleteMissing", true);

            Map<String, Object> discoveryRules = new HashMap<>();
            discoveryRules.put("createMissing", true);
            discoveryRules.put("updateExisting", true);
            discoveryRules.put("deleteMissing", true);

            Map<String, Object> graphs = new HashMap<>();
            graphs.put("createMissing", true);
            graphs.put("updateExisting", true);
            graphs.put("deleteMissing", true);

            Map<String, Object> groups = new HashMap<>();
            groups.put("createMissing", true);

            Map<String, Object> hosts = new HashMap<>();
            hosts.put("createMissing", true);
            hosts.put("updateExisting", true);

            Map<String, Object> images = new HashMap<>();
            images.put("createMissing", true);
            images.put("updateExisting", true);

            Map<String, Object> items = new HashMap<>();
            items.put("createMissing", true);
            items.put("updateExisting", true);
            items.put("deleteMissing", true);


            Map<String, Object> maps = new HashMap<>();
            maps.put("createMissing", true);
            maps.put("updateExisting", true);

            Map<String, Object> screens = new HashMap<>();
            screens.put("createMissing", true);
            screens.put("updateExisting", true);

            Map<String, Object> templateLinkage = new HashMap<>();
            templateLinkage.put("createMissing", true);

            Map<String, Object> ztemplates = new HashMap<>();
            ztemplates.put("createMissing", true);
            ztemplates.put("updateExisting", true);

            Map<String, Object> templateScreens = new HashMap<>();
            templateScreens.put("createMissing", true);
            templateScreens.put("updateExisting", true);
            templateScreens.put("deleteMissing", true);

            Map<String, Object> triggers = new HashMap<>();
            triggers.put("createMissing", true);
            triggers.put("updateExisting", true);
            triggers.put("deleteMissing", true);

            Map<String, Object> valueMaps = new HashMap<>();
            valueMaps.put("createMissing", true);
            valueMaps.put("updateExisting", true);


            rules96.put("applications", applications);
            rules96.put("discoveryRules", discoveryRules);
            rules96.put("graphs", graphs);
            rules96.put("groups", groups);
            rules96.put("hosts", hosts);
            rules96.put("images", images);
            rules96.put("items", items);
            rules96.put("maps", maps);
            rules96.put("screens", screens);
            rules96.put("templateLinkage", templateLinkage);
            rules96.put("templates", ztemplates);
            rules96.put("templateScreens", templateScreens);
            rules96.put("triggers", triggers);
            rules96.put("valueMaps", valueMaps);
            Map<String, Object> response96 = zabbixService.call96("configuration.import", params96);
            if (!response96.containsKey("result")) {
                System.out.println("导入主机出错:"+hostItemList+"\t出错原因:"+response96.toString());
            }
        } else {
            System.out.println("导出主机出错:"+hostItemList+"\t出错原因:"+response75.toString());
        }
    }
        return "ok";
    }

    public void readFailHost() throws IOException {
        //读取文件中的失败主机号
//        File file = new File("");
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        String line;
//        while((line = br.readLine())!=null)

        //查询主机号
        //直接导入

    }


}
