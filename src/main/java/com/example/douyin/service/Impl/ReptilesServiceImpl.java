package com.example.douyin.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONString;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.douyin.config.CommonQueryConfig;
import com.example.douyin.config.CookieConfig;
import com.example.douyin.service.IReptilesService;
import com.example.douyin.service.JsMethods;
import com.example.douyin.util.JsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
@Service
public class ReptilesServiceImpl implements IReptilesService {

    @Autowired
    private CookieConfig cookieConfig;

    @Autowired
    private CommonQueryConfig commonQueryConfig;

    @Override
    public JSONObject getXbogus(String url, String userAgent) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("url",url);
        paramMap.put("user_agent",userAgent);
        String body = HttpRequest.post("http://127.0.0.1:8787/X-Bogus")
                .header(Header.CONTENT_TYPE, String.valueOf(ContentType.JSON))
                .body(JSONObject.toJSONString(paramMap))
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        String param = (String) jsonObject.get("param");
        commonQueryConfig.setParam(param);
        return jsonObject;
    }

    @Override
    public String getTTwid() {
        String post = "{\"region\":\"cn\",\"aid\":1768,\"needFid\":false,\"service\":\"www.ixigua.com\",\"migrate_info\":{\"ticket\":\"\",\"source\":\"node\"},\"cbUrlProtocol\":\"https\",\"union\":true}";
        String body = HttpRequest.post("https://ttwid.bytedance.com/ttwid/union/register/")
                .body(post)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        String redirectUrl = (String) jsonObject.get("redirect_url");
        int statusCode = (int) jsonObject.get("status_code");
        String ttwidValue = "";
        if (statusCode == 0){
            HttpCookie ttwid = HttpRequest.get(redirectUrl)
                    .execute()
                    .getCookie("ttwid");
            ttwidValue = ttwid.getValue();
            cookieConfig.setTtwid(ttwidValue);
        }else {

        }
        return ttwidValue;
    }

    @Override
    public String getCookie() {
        String ttwid = cookieConfig.getTtwid();
        if (StringUtils.isEmpty(ttwid)){
            ttwid = getTTwid();
            cookieConfig.setTtwid(ttwid);
        }
        String bdTicketGuardClientData = cookieConfig.getBdTicketGuardClientData();
        String cookie = "ttwid="+ttwid+";bd_ticket_guard_client_data="+bdTicketGuardClientData;
        return cookie;
    }

    @Override
    public JSONObject getUserFavoriteList(String secUserId, String maxCursor, String minCursor) {
        //https://www.douyin.com/aweme/v1/web/aweme/favorite/?device_platform=webapp&aid=6383&sec_user_id=MS4wLjABAAAApmi-USaKOChKt5pX20FjhjJMcH2bFRfh04i2aP-zVlI&X-Bogus=DFSzswSLLuXANJ0qtc3okU9WcBng
        String url = "https://www.douyin.com/aweme/v1/web/aweme/favorite/";
            url += "?device_platform="+commonQueryConfig.getDevicePlatform()
                    +"&aid="+commonQueryConfig.getAid()
                    +"&sec_user_id="+secUserId
                    +"&max_cursor="+(Long.parseLong(maxCursor) > 0 ? maxCursor : "0")
                    +"&min_cursor="+(Long.parseLong(minCursor) > 0 ? maxCursor : "0");
            getXbogus(url,commonQueryConfig.getUserAgent());

        String body = HttpRequest.get(commonQueryConfig.getParam())
                .header(Header.COOKIE,StringUtils.isEmpty(cookieConfig.getCookie()) ? getCookie() : cookieConfig.getCookie())
                .header(Header.USER_AGENT,commonQueryConfig.getUserAgent())
                .header(Header.REFERER,"https://www.douyin.com/user/"+secUserId)
                .execute()
                .body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        JSONArray aweme_list = (JSONArray) jsonObject.get("aweme_list");
        JSONArray collect = aweme_list.stream().map(item -> {
            JSONObject temp = new JSONObject();
            JSONObject object = (JSONObject) item;
            String previewTitle = (String) object.get("preview_title");
            JSONObject author = (JSONObject) object.get("author");
            String secUid = (String) author.get("sec_uid");
            String awemeId = (String) object.get("aweme_id");
            temp.put("previewTitle", previewTitle);
            temp.put("authorSecUid", secUid);
            temp.put("awemeId", awemeId);
            temp.put("createTime",  object.get("create_time"));
            return temp;
        }).collect(Collectors.toCollection(JSONArray::new));
        JSONObject res = new JSONObject();
        res.put("aweme_list",collect);
        res.put("max_cursor",jsonObject.get("max_cursor"));
        res.put("status_code",jsonObject.get("status_code"));
        return res;
    }
}
