package com.lzw.springcloud.controller;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzw.springcloud.service.IZabbixService;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.apache.commons.lang.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

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
    @RequestMapping("zabbix/changeProxy")
    public void findAndChangeProxy(){
        String[] strings = {
        "12982" ,"12992" ,"12993" ,"10329" ,"12498" ,"12499" ,"12503" ,"12514" ,"12589" ,"12590"
        ,"12591"
        ,"12592"
        ,"12608"
        ,"12610"
        ,"12612"
        ,"12618"
        ,"12620"
        ,"12625"
        ,"12686"
        ,"12701"
        ,"12685"
        ,"12508"
        ,"12587"
        ,"12616"
        ,"12664"
        ,"12489"
        ,"12693"
        ,"12697"
        ,"12764"
        ,"12500"
        ,"12502"
        ,"12984"
        ,"13870"
        ,"12375"
        ,"12507"
        ,"12677"
        ,"12676"
        ,"12487"
        ,"12586"
        ,"12614"
        ,"12635"
        ,"12687"
        ,"12688"
        ,"12689"
        ,"12690"
        ,"12983"
        ,"12691"
        ,"12692"
        ,"12694"
        ,"12695"
        ,"12493"
        ,"12496"
        ,"12622"
        ,"12699"
        ,"11568"
        ,"11569"
        ,"12987"
        ,"12991"
        ,"12997"
        ,"12280"
        ,"12988"
        ,"12994"
        ,"12995"
        ,"13362"
        ,"12497"
        ,"13377"
        ,"12029"
        ,"12064"
        ,"12996"
        ,"12043"
        ,"12264"
        ,"12247"
        ,"12248"
        ,"12269"
        ,"12250"
        ,"12281"
        ,"12252"
        ,"12222"
        ,"12322"
        ,"12361"
        ,"12434"
        ,"12254"
        ,"12350"
        ,"12351"
        ,"12353"
        ,"12346"
        ,"12160"
        ,"12161"
        ,"12167"
        ,"12223"
        ,"12227"
        ,"12265"
        ,"12266"
        ,"12271"
        ,"12279"
        ,"12512"
        ,"12309"
        ,"12310"
        ,"12311"
        ,"12521"
        ,    "12156"
        ,    "13868"
        ,    "12588"
        ,    "12623"
        ,    "12629"
        ,    "12678"
        ,    "12283"
        ,    "12225"
        ,    "12226"
        ,    "12505"
        ,    "12510"
        ,    "12519"
        ,    "12628"
        ,    "12679"
        ,    "12680"
        ,    "12681"
        ,    "12682"
        ,    "12683"
        ,    "12684"
        ,    "12985"
        ,    "12986"
        ,    "12990"
        ,    "12263"
        ,    "12259"
        ,    "10204"
        ,    "12515"
        ,    "12518"
        ,    "12520"
        ,    "12989"
        ,    "12241"
        ,    "12763"
        ,    "14051"
        ,    "14064"
        ,    "10186"
        ,    "12336"
        ,    "12486"
        ,    "12494"
        ,    "13402"
        ,    "12270"
        ,    "12335"
        ,    "12347"
        ,    "12488"
        ,    "12495"
        ,    "12501"
        ,    "12312"
        ,    "12333"
        ,    "12334"
        ,    "12492"
        ,    "14049"
        ,    "12491"
        ,    "12245"
        ,    "14071"
        ,    "12354"
        ,    "12422"};
        ArrayList<String> list80 =  new ArrayList<String>(Arrays.asList(strings));

        for(int i=0;i<list80.size()/2;i++){
            Map<String, Object> itemParams = new HashMap<>();
            itemParams.put("proxy_hostid","12999");
            itemParams.put("hostid",list80.get(i) );
            Map<String, Object> itemRepsonse = zabbixService.call75("host.update", itemParams);
            if (!itemRepsonse.containsKey("result")) {
                System.out.println("79更改代理出错:"+list80.get(i)+"\t出错原因:"+itemRepsonse.toString());
            }
        }
        for(int i=list80.size()/2;i<list80.size();i++){
            Map<String, Object> itemParams = new HashMap<>();
            itemParams.put("proxy_hostid","10497");
            itemParams.put("hostid",list80.get(i) );
            Map<String, Object> itemRepsonse = zabbixService.call75("host.update", itemParams);
            if (!itemRepsonse.containsKey("result")) {
                System.out.println("76更改代理出错:"+list80.get(i)+"\t出错原因:"+itemRepsonse.toString());
            }
        }


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



    //1.查询主机记录没有这个ip的主机
    //2.导入主机到98
    //3.记录导入失败的主机
    public  void  exportAndImportZabbixHostConfig96To98() throws IOException {
        FileReader fr = new FileReader(new File(""));
        BufferedReader br = new BufferedReader(fr);
        List<String> hosts = new ArrayList<>();
        String line;
        while((line=br.readLine())!=null){
            hosts.add(line);
        }

    }
    @RequestMapping("zabbix/enable/hosts")
    public  void  enableZabbixHost() throws IOException {
        FileReader fr = new FileReader(new File("C:\\Users\\a9747\\Desktop\\10000号.txt"));
        BufferedReader br = new BufferedReader(fr);
        List<String> hosts = new ArrayList<>();
        List<String> failHosts = new ArrayList<>();
        String line;
        while((line=br.readLine())!=null){
            hosts.add(line);
        }
        //2.掉api
        //调用zabbix 修改监控 api
        //设置参数
        for(String hostId :hosts) {
            Map<String, Object> params75 = new HashMap<>();
            Map<String, Object> options75 = new HashMap<>();
            params75.put("status", "0");
            params75.put("hostid", hostId.trim());
            Map<String, Object> response75 = zabbixService.call75("host.update", params75);
            if (!response75.containsKey("result")) {
                failHosts.add(hostId+"@@@:"+response75.get("error").toString());
            }
        }
        System.out.println("失败主机hostid:"+failHosts);
    }


    @RequestMapping("zabbix/find/hosts")
    public void  findGroupsHost() throws IOException {
        FileWriter fw = new FileWriter("");
        BufferedWriter bw = new BufferedWriter(fw);
        Map<String, Object> params75 = new HashMap<>();
        params75.put("monitored_hosts",true);
        Map<String, Object> repsonse = zabbixService.call75("host.get", params75);
        JSONArray resultArray =(JSONArray) repsonse.get("result");
        for(int i=0;i<resultArray.size();i++){
            hostList.add((String)resultArray.getJSONObject(i).get("hostid"));
        }
        System.out.println(repsonse);
    }

    @RequestMapping("zabbix/change/null")
    public void changeProxyForNull(){
        String[]  hostStringList = {
                "13323",
                "13325",
                "13329",
                "13333",
                "13335",
                "13373",
                "13330",
                "13640",
                "13648",
                "13650",
                "13673",
                "13678",
                "13680",
                "13695",
                "13651",
                "11894",
                "11903",
                "13646",
                "13669",
                "13679",
                "14053",
                "13637",
                "13642",
                "13647",
                "13652",
                "13656",
                "13660",
                "13662",
                "13666",
                "11632",
                "11648",
                "13643",
                "13659",
                "11594",
                "13665",
                "13675",
                "13682",
                "11887",
                "11890",
                "11898",
                "11901",
                "13645",
                "13657",
                "13658",
                "13661",
                "13671",
                "13672",
                "12753",
                "12722",
                "12754",
                "12265",
                "12266",
                "12264",
                "12726",
                "12263",
                "13297",
                "13300",
                "13302",
                "13304",
                "13332",
                "13334",
                "13374",
                "13376",
                "13639",
                "13641",
                "13653",
                "13654",
                "13663",
                "13664",
                "13667",
                "13674",
                "13677",
                "14054",
                "13324",
                "13655",
                "13299",
                "13326",
                "13327",
                "13328",
                "13331",
                "13336",
                "13649",
                "13696",
                "13694",
                "14061",
                "13244",
                "13638",
                "13301",
                "13375",
                "13644",
                "13668",
                "13670",
                "13681",
                "10112",
                "10172",
                "10173",
                "10174",
                "10177",
                "10178",
                "10186",
                "10778",
                "10347",
                "10442",
                "10329",
                "10409",
                "10444",
                "10357",
                "10545",
                "10398",
                "10413",
                "10548",
                "10418",
                "10433",
                "10450",
                "10452",
                "10453",
                "10514",
                "10524",
                "10518",
                "10521",
                "10525",
                "10528",
                "10554",
                "10475",
                "10532",
                "10729",
                "10730",
                "10776",
                "11181",
                "11182",
                "11183",
                "11110",
                "11111",
                "11112",
                "11113",
                "11151",
                "11193",
                "11202",
                "11207",
                "11209",
                "11211",
                "10175",
                "10176",
                "10179",
                "11005",
                "10204",
                "10355",
                "10500",
                "10503",
                "10346",
                "10522",
                "10538",
                "10512",
                "10519",
                "10374",
                "10523",
                "10526",
                "10386",
                "10387",
                "10535",
                "10389",
                "10402",
                "10505",
                "10509",
                "10513",
                "10427",
                "10432",
                "10441",
                "10463",
                "10464",
                "10511",
                "10539",
                "10549",
                "10550",
                "10551",
                "10552",
                "10553",
                "11200",
                "11201",
                "11203",
                "11053",
                "11054",
                "11055",
                "11056",
                "11060",
                "11061",
                "11062",
                "11063",
                "11065",
                "11067",
                "11204",
                "11205",
                "11206",
                "11208",
                "11220",
                "11225",
                "10113",
                "10131",
                "11195",
                "10160",
                "10168",
                "10169",
                "10438",
                "10182",
                "10183",
                "10339",
                "10341",
                "10502",
                "10342",
                "10344",
                "10349",
                "10350",
                "10351",
                "10533",
                "10529",
                "10520",
                "10516",
                "10536",
                "10383",
                "10384",
                "10390",
                "10391",
                "10530",
                "10405",
                "10537",
                "10510",
                "10419",
                "10424",
                "11107",
                "10428",
                "10429",
                "10437",
                "10542",
                "10440",
                "10462",
                "10543",
                "10546",
                "10487",
                "10489",
                "10749",
                "11194",
                "11196",
                "11166",
                "11197",
                "11198",
                "11199",
                "12757",
                "10109",
                "10171",
                "10111",
                "10208",
                "10358",
                "11064",
                "10447",
                "10340",
                "10359",
                "10547",
                "10392",
                "10508",
                "10491",
                "10407",
                "10408",
                "10426",
                "10448",
                "10449",
                "10473",
                "10495",
                "10506",
                "10515",
                "11057",
                "11069",
                "11070",
                "11071",
                "11074",
                "11076",
                "11077",
                "11078",
                "11079",
                "11080",
                "11081",
                "11083",
                "11084",
                "11085",
                "11086",
                "11087",
                "11088",
                "11089",
                "11090",
                "11091",
                "11092",
                "11093",
                "11095",
                "11096",
                "11097",
                "11098",
                "11099",
                "11100",
                "11101",
                "11102",
                "11105",
                "11106",
                "11108",
                "10504",
                "12952",
                "10180",
                "10431",
                "10348",
                "10343",
                "10352",
                "10353",
                "10354",
                "10356",
                "10540",
                "10534",
                "10382",
                "10385",
                "11014",
                "10531",
                "10400",
                "10401",
                "10403",
                "10415",
                "10420",
                "10422",
                "10430",
                "10434",
                "10436",
                "10468",
                "10541",
                "10478",
                "10479",
                "10480",
                "10544",
                "10496",
                "10750",
                "10780",
                "11034",
                "11035",
                "11037",
                "11038",
                "11039",
                "11040",
                "11041",
                "11042",
                "11043",
                "11047",
                "12982",
                "11049",
                "11050",
                "11051",
                "12988",
                "12990",
                "12992",
                "12994",
                "12996",
                "11167",
                "11168",
                "12395",
                "11261",
                "11262",
                "11263",
                "11264",
                "11265",
                "11266",
                "11267",
                "11268",
                "11269",
                "11270",
                "11271",
                "11272",
                "11273",
                "11274",
                "11276",
                "11277",
                "11278",
                "11279",
                "11280",
                "11281",
                "11282",
                "11283",
                "11284",
                "11285",
                "11286",
                "11287",
                "11288",
                "11289",
                "11290",
                "11291",
                "11294",
                "12398",
                "12399",
                "12400",
                "12401",
                "12402",
                "12403",
                "12404",
                "12405",
                "12406",
                "12407",
                "12408",
                "12409",
                "12410",
                "12411",
                "12412",
                "12413",
                "12414",
                "12415",
                "12416",
                "12417",
                "12418",
                "11318",
                "11319",
                "11320",
                "11321",
                "11322",
                "11323",
                "11324",
                "11325",
                "11326",
                "11327",
                "11328",
                "11331",
                "11332",
                "11333",
                "11334",
                "11336",
                "11337",
                "11338",
                "11339",
                "12419",
                "12420",
                "12421",
                "11347",
                "11349",
                "12422",
                "11360",
                "11364",
                "11420",
                "11423",
                "11448",
                "11504",
                "11505",
                "11506",
                "11728",
                "11729",
                "11451",
                "11452",
                "11453",
                "11454",
                "11455",
                "11456",
                "11457",
                "11458",
                "11459",
                "11460",
                "11462",
                "11463",
                "11464",
                "11465",
                "11466",
                "11467",
                "11468",
                "11469",
                "11470",
                "11471",
                "11472",
                "11473",
                "11474",
                "11475",
                "11476",
                "11477",
                "11478",
                "11479",
                "11480",
                "11481",
                "11482",
                "11483",
                "11484",
                "11485",
                "11486",
                "11487",
                "11488",
                "11489",
                "11490",
                "11491",
                "11492",
                "11493",
                "11495",
                "11496",
                "11497",
                "11498",
                "11507",
                "11256",
                "11258",
                "11501",
                "11502",
                "11447",
                "11449",
                "11450",
                "11461",
                "11503",
                "12157",
                "13269",
                "11329",
                "11295",
                "11340",
                "11342",
                "11354",
                "11359",
                "11362",
                "11395",
                "11396",
                "11406",
                "11407",
                "11408",
                "11410",
                "11411",
                "11412",
                "11413",
                "11414",
                "11419",
                "11424",
                "11428",
                "11434",
                "11442",
                "11445",
                "14032",
                "14034",
                "11727",
                "11344",
                "12764",
                "11350",
                "11352",
                "11409",
                "12768",
                "12772",
                "12781",
                "12800",
                "11356",
                "11366",
                "11368",
                "11370",
                "11371",
                "11372",
                "11373",
                "11378",
                "11379",
                "11382",
                "11383",
                "11384",
                "11385",
                "11386",
                "11387",
                "11388",
                "11389",
                "11390",
                "11391",
                "11392",
                "11393",
                "11394",
                "11397",
                "11398",
                "11399",
                "11400",
                "11401",
                "11402",
                "11403",
                "11404",
                "11405",
                "11415",
                "11416",
                "11417",
                "11418",
                "11421",
                "11422",
                "11425",
                "11426",
                "11427",
                "11429",
                "11430",
                "11431",
                "11432",
                "11433",
                "11435",
                "11436",
                "11437",
                "11438",
                "11439",
                "11440",
                "11441",
                "11443",
                "11444",
                "11494",
                "11508",
                "14048",
                "12803",
                "12334",
                "12333",
                "12335",
                "12336",
                "12378",
                "12393",
                "12512",
                "12514",
                "12515",
                "12516",
                "12518",
                "12520",
                "12521",
                "12532",
                "12533",
                "12534",
                "12536",
                "12537",
                "12538",
                "12539",
                "12561",
                "12562",
                "12570",
                "12589",
                "12590",
                "12591",
                "12592",
                "12596",
                "12603",
                "12606",
                "12607",
                "12618",
                "12620",
                "12629",
                "12631",
                "12632",
                "12644",
                "12645",
                "12646",
                "12657",
                "12658",
                "12669",
                "12670",
                "12671",
                "12678",
                "12679",
                "12680",
                "12693",
                "12756",
                "12320",
                "12508",
                "12580",
                "12628",
                "12661",
                "12686",
                "12713",
                "12750",
                "12782",
                "12950",
                "12998",
                "12211",
                "12345",
                "12396",
                "12397",
                "12517",
                "12586",
                "12588",
                "12601",
                "12602",
                "12604",
                "12614",
                "12642",
                "12635",
                "12659",
                "12687",
                "12688",
                "12689",
                "12690",
                "12691",
                "12692",
                "12694",
                "12695",
                "12740",
                "12375",
                "12511",
                "12519",
                "12523",
                "12524",
                "12526",
                "12527",
                "12528",
                "12529",
                "12997",
                "12609",
                "12611",
                "12613",
                "12616",
                "12640",
                "12630",
                "12641",
                "12643",
                "12649",
                "12650",
                "12653",
                "12654",
                "12656",
                "12703",
                "12984",
                "12983",
                "12985",
                "12986",
                "12987",
                "12991",
                "12995",
                "12394",
                "12380",
                "12423",
                "12495",
                "12513",
                "12522",
                "12525",
                "12542",
                "12543",
                "12993",
                "13024",
                "12577",
                "12578",
                "12579",
                "12585",
                "12608",
                "12610",
                "12612",
                "12615",
                "12617",
                "12619",
                "12621",
                "12638",
                "12648",
                "12655",
                "12663",
                "12665",
                "12668",
                "12674",
                "12677",
                "12681",
                "12682",
                "12683",
                "12684",
                "12685",
                "12779",
                "12951",
                "12505",
                "12507",
                "12509",
                "12510",
                "12544",
                "12545",
                "12566",
                "12568",
                "12587",
                "12594",
                "12595",
                "12597",
                "12598",
                "12599",
                "12600",
                "12605",
                "12622",
                "12636",
                "12637",
                "12662",
                "12664",
                "12666",
                "12667",
                "12672",
                "12675",
                "12706",
                "12741",
                "12774",
                "12949",
                "12504",
                "12530",
                "12531",
                "12535",
                "12989",
                "12541",
                "12556",
                "12557",
                "12559",
                "12560",
                "12563",
                "12564",
                "12581",
                "12582",
                "12583",
                "12584",
                "12593",
                "12623",
                "12625",
                "12639",
                "12647",
                "12660",
                "12673",
                "12676",
                "12697",
                "12699",
                "12763",
                "12798",
                "14029",
                "14033",
                "14039",
                "14045",
                "14046",
                "14047",
                "14049",
                "14051",
                "13277",
                "13278",
                "13293",
                "14031",
                "10204",
                "13280",
                "13288",
                "13289",
                "13290",
                "13294",
                "14036",
                "14037",
                "14038",
                "14035",
                "14050"
        };
        ArrayList<String> lists = new ArrayList<>(Arrays.asList(hostStringList));
        int i=0;
        for(String item :lists) {
            Map<String, Object> params75 = new HashMap<>();
            params75.put("hostids", item);
            Map<String, Object> repsonse = zabbixService.call75("host.get", params75);
            if (!repsonse.containsKey("result")) {
                System.out.println("导入主机出错:"+item+"\t出错原因:"+repsonse.toString());
            }else {
                JSONObject resultJsonObject = JSONObject.parseObject(repsonse.toString());
                JSONArray resultArray = resultJsonObject.getJSONArray("result");
                String proxy_hostid = resultArray.getJSONObject(0).getString("proxy_hostid");
                if(proxy_hostid.equals("0")){
                    i++;
                    if(i%2==1){
                        Map<String, Object> itemParams = new HashMap<>();
                        itemParams.put("proxy_hostid","10497");
                        itemParams.put("hostid",item );
                        Map<String, Object> itemRepsonse = zabbixService.call75("host.update", itemParams);
                        if (!itemRepsonse.containsKey("result")) {
                            System.out.println("76更改代理出错:"+item+"\t出错原因:"+itemRepsonse.toString());
                        }
                    }else{
                        Map<String, Object> itemParams = new HashMap<>();
                        itemParams.put("proxy_hostid","12999");
                        itemParams.put("hostid",item );
                        Map<String, Object> itemRepsonse = zabbixService.call75("host.update", itemParams);
                        if (!itemRepsonse.containsKey("result")) {
                            System.out.println("79更改代理出错:"+item+"\t出错原因:"+itemRepsonse.toString());
                        }
                    }
                }
            }
        }
    }

    @RequestMapping("zabbix/change/8082")
    public void  change96To8082(){
        String[]  hostStringList = {
                "10328",
                "10329",
                "10330",
                "10341",
                "10360",
                "10361",
                "10362",
                "10389",
                "10487",
                "10489",
                "10494",
                "10498",
                "10499",
                "10500",
                "10510",
                "10564",
                "10565",
                "10567",
                "10568",
                "10583",
                "10584",
                "10590",
                "10591",
                "10593",
                "10594",
                "10595",
                "10602",
                "10609",
                "10633",
                "10634",
                "10639",
                "10640",
                "10641",
                "10643",
                "10644",
                "10645",
                "10646",
                "10650",
                "10653",
                "10656",
                "10660",
                "10668",
                "10669",
                "10670",
                "10671",
                "10673",
                "10677",
                "10684",
                "10685",
                "10686",
                "10687",
                "10699",
                "10700",
                "10706",
                "10707",
                "10709",
                "10710",
                "10712",
                "10714",
                "10716",
                "10717",
                "10718",
                "10720",
                "10721",
                "10722",
                "10735",
                "10736",
                "10738",
                "10741",
                "10742",
                "10743",
                "10744",
                "10746",
                "10747",
                "10748",
                "10749",
                "10750",
                "10751",
                "10752",
                "10753",
                "10754",
                "10755",
                "10756",
                "10757",
                "10758",
                "10759",
                "10771",
                "10772",
                "10775",
                "10777",
                "10779",
                "10780",
                "10783",
                "10787",
                "10794",
                "10796",
                "10797",
                "10798",
                "10800",
                "10818",
                "10823",
                "10829",
                "10832",
                "10847",
                "10848",
                "10849",
                "10850",
                "10923",
                "10924",
                "10925",
                "10930",
                "10931",
                "10953",
                "10998",
                "11001",
                "11002",
                "11003",
                "11004",
                "11007",
                "11038",
                "11057",
                "11067",
                "11070",
                "11072",
                "11073",
                "11075",
                "11081",
                "11092",
                "11108",
                "11110",
                "11111",
                "11118",
                "11123",
                "11124",
                "11127",
                "11129",
                "11130",
                "11137",
                "11138",
                "11139",
                "11140",
                "11141",
                "11142",
                "11143",
                "11147",
                "11164",
                "11166",
                "11167",
                "11168",
                "11169",
                "11170",
                "11171",
                "11172",
                "11174",
                "11175",
                "11180",
                "11187",
                "11188",
                "11189",
                "11203",
                "11204",
                "11219",
                "11220",
                "11221",
                "11222",
                "11231",
                "11233",
                "11249",
                "11250",
                "11251",
                "11252",
                "11264",
                "11269",
                "11271",
                "11281",
                "11316",
                "11322",
                "11323",
                "11324",
                "11325",
                "11326",
                "11327",
                "11340",
                "11341",
                "11342",
                "11343",
                "11344",
                "11345",
                "11351",
                "11358",
                "11359",
                "11360",
                "11361",
                "11362",
                "11364",
                "11379",
                "11380",
                "11381",
                "11382",
                "11385",
                "11389",
                "11391",
                "11392",
                "11397",
                "11401",
                "11402",
                "11403",
                "11404",
                "11405",
                "11406",
                "11407",
                "11408",
                "11409",
                "11417",
                "11418",
                "11431",
                "11432",
                "11433",
                "11435",
                "11438",
                "11445",
                "11451",
                "11457",
                "11460",
                "11461",
                "11466",
                "11468",
                "11472",
                "11475",
                "11481",
                "11483",
                "11484",
                "11485",
                "11493",
                "11494",
                "11495",
                "11496",
                "11497",
                "11500",
                "11501",
                "11502",
                "11509",
                "11510",
                "11511",
                "11512",
                "11513",
                "11514",
                "11519",
                "11520",
                "11521",
                "11522",
                "11523",
                "11524",
                "11526",
                "11527",
                "11528",
                "11529",
                "11538",
                "11541",
                "11543",
                "11546",
                "11547",
                "11548",
                "11549",
                "11550",
                "11551",
                "11552",
                "11553",
                "11554",
                "11555",
                "11557",
                "11560",
                "11561",
                "11562",
                "11563",
                "11564",
                "11569",
                "11572",
                "11573",
                "11575",
                "11576",
                "11577",
                "11578",
                "11580",
                "11586",
                "11593",
                "11597",
                "11602",
                "11633",
                "11634",
                "11635",
                "11636",
                "11637",
                "11638",
                "11639",
                "11640",
                "11661",
                "11662",
                "11663",
                "11666",
                "11668",
                "11676",
                "11677",
                "11691",
                "11697",
                "11698",
                "11699",
                "11700",
                "11701",
                "11702",
                "11703",
                "11704",
                "11705",
                "11706",
                "11707",
                "11708",
                "11709",
                "11712",
                "11717",
                "11719",
                "11720",
                "11726",
                "11727",
                "11728",
                "11729",
                "11730",
                "11731",
                "11732",
                "11733",
                "11734",
                "11735",
                "11736",
                "11737",
                "11738",
                "11739",
                "11740",
                "11741",
                "11742",
                "11743",
                "11744",
                "11745",
                "11746",
                "11747",
                "11748",
                "11751",
                "11752",
                "11753",
                "11754",
                "11755",
                "11756",
                "11758",
                "11759",
                "11760",
                "11761",
                "11762",
                "11780",
                "11785",
                "11787",
                "11789",
                "11793",
                "11794",
                "11795",
                "11796",
                "11797",
                "11805",
                "11809",
                "11810",
                "11811",
                "11820",
                "11828",
                "11829",
                "11830",
                "11831",
                "11832",
                "11834",
                "11835",
                "11839",
                "11840",
                "11853",
                "11861",
                "11862",
                "11863",
                "11864",
                "11865",
                "11866",
                "11867",
                "11868",
                "11869",
                "11870",
                "11871",
                "11872",
                "11873",
                "11874",
                "11878",
                "11882",
                "11897",
                "11898",
                "11903",
                "11919",
                "11922",
                "11924",
                "11940",
                "11941",
                "11963",
                "11981",
                "11985",
                "11992",
                "11997",
                "11999",
                "12001",
                "12002",
                "12003",
                "12008",
                "12010",
                "12011",
                "12012",
                "12013",
                "12049",
                "12050",
                "12051",
                "12052",
                "12053",
                "12054",
                "12055",
                "12056",
                "12057",
                "12060",
                "12066",
                "12069",
                "12070",
                "12077",
                "12078",
                "12082",
                "12084",
                "12085",
                "12086",
                "12087",
                "12094",
                "12096",
                "12099",
                "12123",
                "12127",
                "12131",
                "12132",
                "12133",
                "12134",
                "12136",
                "12140",
                "12142",
                "12154",
                "12156",
                "12164",
                "12166",
                "12167",
                "12168",
                "12171",
                "12196",
                "12199",
                "12205",
                "12207",
                "12208",
                "12220",
                "12224",
                "12225",
                "12228",
                "12236",
                "12239",
                "12243",
                "12244",
                "12247",
                "12254",
                "12255",
                "12265",
                "12266",
                "12267",
                "12283",
                "12292",
                "12295",
                "12299",
                "12300",
                "12301",
                "12302",
                "12309",
                "12312",
                "12313",
                "12314",
                "12316",
                "12340",
                "12342",
                "12343",
                "12344",
                "12345",
                "12346",
                "12347",
                "12348",
                "12370",
                "12372",
                "12435"
        };
        ArrayList<String> lists = new ArrayList<>(Arrays.asList(hostStringList));
        int i=0;
        for(String item :lists) {
            Map<String, Object> params96 = new HashMap<>();
            params96.put("hostids", item);
            Map<String, Object> repsonse = zabbixService.call96("host.get", params96);
            if (!repsonse.containsKey("result")) {
                System.out.println("修改主机出错:"+item+"\t出错原因:"+repsonse.toString());
            }else {
                JSONObject resultJsonObject = JSONObject.parseObject(repsonse.toString());
                JSONArray resultArray = resultJsonObject.getJSONArray("result");
                String proxy_hostid = resultArray.getJSONObject(0).getString("proxy_hostid");
                if(proxy_hostid.equals("10270")||proxy_hostid.equals("0")){
                    i++;
                    if(i%2==1){
                        Map<String, Object> itemParams = new HashMap<>();
                        itemParams.put("proxy_hostid","12361");
                        itemParams.put("hostid",item );
                        Map<String, Object> itemRepsonse = zabbixService.call96("host.update", itemParams);
                        if (!itemRepsonse.containsKey("result")) {
                            System.out.println("82更改代理出错:"+item+"\t出错原因:"+itemRepsonse.toString());
                        }
                    }else{
                        Map<String, Object> itemParams = new HashMap<>();
                        itemParams.put("proxy_hostid","12437");
                        itemParams.put("hostid",item );
                        Map<String, Object> itemRepsonse = zabbixService.call96("host.update", itemParams);
                        if (!itemRepsonse.containsKey("result")) {
                            System.out.println("80更改代理出错:"+item+"\t出错原因:"+itemRepsonse.toString());
                        }
                    }
                }
            }
        }
    }

}


