package com.lzw.springcloud.utils;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

public class ZabbixConnection75 {
    private static ZabbixApi api;

    private final static String url = "https://136.5.178.75/api_jsonrpc.php";

    public final static String username = "Admin";

    public final static String password = "Zabbix#ustc996";

    public static ZabbixApi connectZabbix(){
        try {
            if (null == api) {
                api = new DefaultZabbixApi(url, SkipHttpsUtil.wrapClient());
                api.init();
                api.login(username, password);
            }
        } catch (Exception e) {
            api = null;
        }
        return api;
    }
}
