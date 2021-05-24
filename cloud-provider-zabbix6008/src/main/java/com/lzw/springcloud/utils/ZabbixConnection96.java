package com.lzw.springcloud.utils;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

public class ZabbixConnection96 {
    private static ZabbixApi api;

    private final static String url = "https://136.6.248.96:8443/api_jsonrpc.php";

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
