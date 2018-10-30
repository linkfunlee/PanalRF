package com.netease.nim.demo;

import com.netease.nimlib.sdk.ServerAddresses;

/**
 * 网易云信私有化配置项
 */
class PrivatizationConfig {

    static ServerAddresses getServerAddresses() {
        return null;
    }

    static String getAppKey() {
        return null;
    }

    private static ServerAddresses get() {
        ServerAddresses addresses = new ServerAddresses();
        addresses.nosDownload = "nos.netease.com";
        addresses.nosAccess = "{bucket}.nosdn.127.net/{object}";
        return addresses;
    }
   /***
    * 类名 PrivatizationConfig
   *@作者 Administrator
   *@创建日期 2018/10/9 15:32
   */
   /***
    * 类名 PrivatizationConfig
   *@author 
   *@创建日期 2018/10/9 15:34
   */
   /***
    * 类名 PrivatizationConfig
   *@author Administrator
   *@创建日期 2018/10/9 15:36
   */
}
