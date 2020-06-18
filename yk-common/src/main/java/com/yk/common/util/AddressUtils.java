package com.yk.common.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: YK-Platform
 * @description: 获取地址类
 * @author: YuKai Fan
 * @create: 2020-06-18 13:53
 **/
public class AddressUtils {
    private static final Logger logger = LoggerFactory.getLogger(AddressUtils.class);
    public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";


    public static String getRealAddressByIp(String ip) {
        String address = "";

        //内网不查询
        if (ServletUtils.internalIp(ip)) {
            return "内网IP";
        }
        String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
        if (StringUtils.isEmpty(rspStr)) {
            logger.error("获取地理位置异常 {}", ip);
            return address;
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(rspStr);
            JSONObject data = jsonObject.getObject("data", JSONObject.class);
            String region = data.getString("region");
            String city = data.getString("city");
            address = region + " " + city;
        } catch (Exception e) {
            logger.error("获取地理位置异常 {}", ip);
        }
        return address;
    }
}