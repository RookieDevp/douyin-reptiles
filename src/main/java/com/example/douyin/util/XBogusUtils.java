package com.example.douyin.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: ZZJ
 * @date: 2023/03/15
 * @desc:
 */
@Component
public class XBogusUtils {

    private static String signServiceUrl;

    public static String getSignServiceUrl() {
        return signServiceUrl;
    }

    @Value("${sign.service.url}")
    public void setSignServiceUrl(String url) {
        XBogusUtils.signServiceUrl = url;
    }

    public static String signXbogusToUrl(String url, String userAgent) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("url",url);
        paramMap.put("user_agent",userAgent);
        HttpResponse response = HttpRequest.post(signServiceUrl)
                .header(Header.CONTENT_TYPE, String.valueOf(ContentType.JSON))
                .body(JSONObject.toJSONString(paramMap))
                .execute();
        if (response.isOk()){
            JSONObject body = JSONObject.parseObject(response.body());
            return (String) body.get("param");
        }
        return StrUtil.EMPTY;
    }
}
