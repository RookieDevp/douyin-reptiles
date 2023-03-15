package com.example.douyin.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.douyin.config.CommonQueryConfig;
import com.example.douyin.config.CookieConfig;
import com.example.douyin.domain.AjaxResult;
import com.example.douyin.service.IReptilesService;
import com.example.douyin.util.XBogusUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
@Slf4j
@Service
public class ReptilesServiceImpl implements IReptilesService {

    @Autowired
    private CookieConfig cookieConfig;

    @Autowired
    private CommonQueryConfig commonQueryConfig;

    @Value("${sign.service.url}")
    private String signServiceUrl;

    public static final String TTWID_REGISTER_URL = "https://ttwid.bytedance.com/ttwid/union/register/";

    @Override
    public JSONObject getXbogus(String url, String userAgent) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("url",url);
        paramMap.put("user_agent",userAgent);
        HttpResponse response = HttpRequest.post(signServiceUrl)
                .header(Header.CONTENT_TYPE, String.valueOf(ContentType.JSON))
                .body(JSONObject.toJSONString(paramMap))
                .execute();
        if (response.isOk()){
            JSONObject body = JSONObject.parseObject(response.body());
            log.info("param:{}",body.get("param"));
            return body;
        }else {
            return null;
        }
    }

    @Override
    public String getTTwid() {
        String post = "{\"region\":\"cn\",\"aid\":1768,\"needFid\":false,\"service\":\"www.ixigua.com\",\"migrate_info\":{\"ticket\":\"\",\"source\":\"node\"},\"cbUrlProtocol\":\"https\",\"union\":true}";
        HttpResponse httpResponse = HttpRequest.post(TTWID_REGISTER_URL)
                .body(post)
                .execute();
        if (!httpResponse.isOk()){
            return StrUtil.EMPTY;
        }
        JSONObject jsonObject = JSONObject.parseObject(httpResponse.body());
        int statusCode = (int) jsonObject.get("status_code");
        String ttwidValue = "";
        if (statusCode == 0){
            String redirectUrl = (String) jsonObject.get("redirect_url");
            HttpResponse response = HttpRequest.get(redirectUrl)
                    .execute();
            if (response.isOk()) {
                HttpCookie cookie = response.getCookie("ttwid");
                ttwidValue = cookie.getValue();
                cookieConfig.setTtwid(ttwidValue);
            }
        }else {
            return StrUtil.EMPTY;
        }
        return ttwidValue;
    }

    @Override
    public String getCookie() {
        String ttwid = cookieConfig.getTtwid();
        if (StrUtil.isEmpty(ttwid)){
            ttwid = getTTwid();
            if (StrUtil.isEmpty(ttwid)){
                return StrUtil.EMPTY;
            }
            cookieConfig.setTtwid(ttwid);
        }
        String bdTicketGuardClientData = cookieConfig.getBdTicketGuardClientData();
        if (StrUtil.isEmpty(bdTicketGuardClientData)){
            return StrUtil.EMPTY;
        }
        String cookie = "ttwid="+ttwid+";bd_ticket_guard_client_data="+bdTicketGuardClientData;
        return cookie;
    }

    @Override
    public String refreshCookie() {
        String tTwid = getTTwid();
        String bdTicketGuardClientData = cookieConfig.getBdTicketGuardClientData();
        String cookie = "ttwid="+tTwid+";bd_ticket_guard_client_data="+bdTicketGuardClientData;
        return cookie;
    }

    @Override
    public JSONObject getUserFavoriteList(String secUserId, String maxCursor, String minCursor) {
        String url = "https://www.douyin.com/aweme/v1/web/aweme/favorite/";
            url += "?device_platform="+commonQueryConfig.getDevicePlatform()
                    +"&aid="+commonQueryConfig.getAid()
                    +"&sec_user_id="+secUserId
                    +"&max_cursor="+(Long.parseLong(maxCursor) > 0 ? maxCursor : "0")
                    +"&min_cursor="+(Long.parseLong(minCursor) > 0 ? maxCursor : "0");
        String xbogusUrl = XBogusUtils.signXbogusToUrl(url, commonQueryConfig.getUserAgent());
        if (ObjectUtil.isEmpty(xbogusUrl)) {
            log.error("X-Bogus签名失败，xbogusUrl:{}",xbogusUrl);
            return null;
        }
        HttpResponse response = null;
        JSONObject jsonObject = null;
        int statusCode = 0;
        response = HttpRequest.get(xbogusUrl)
                .header(Header.COOKIE, StrUtil.isEmptyIfStr(cookieConfig.getCookie()) ? getCookie() : cookieConfig.getCookie())
                .header(Header.USER_AGENT, commonQueryConfig.getUserAgent())
                .header(Header.REFERER, "https://www.douyin.com/user/" + secUserId)
                .execute();
        if (!response.isOk()){
            return null;
        }
        jsonObject = JSONObject.parseObject(response.body());
        statusCode = (int) jsonObject.get("status_code");
        // 参数不合法，重试一次
        if (statusCode == 5){
            response  = HttpRequest.get(xbogusUrl)
                    .header(Header.COOKIE, refreshCookie())
                    .header(Header.USER_AGENT, commonQueryConfig.getUserAgent())
                    .header(Header.REFERER, "https://www.douyin.com/user/" + secUserId)
                    .execute();
            jsonObject = JSONObject.parseObject(response.body());
            if (!response.isOk()){
                log.error("用户-{}-获取喜欢列表失败,statusCode:5,重试失败！",secUserId);
                return null;
            }
        }
        statusCode = (int) jsonObject.get("status_code");
        JSONArray awemeList = (JSONArray) jsonObject.get("aweme_list");
        if (statusCode != 0 || awemeList == null){
                String statusMsg = (String) jsonObject.get("status_msg");
                log.error("用户{}获取喜欢列表失败,statusCode:{},statusMsg:{}，aweme_list_size：{}",secUserId,statusCode,statusMsg,awemeList == null ? "null" : awemeList.size());
                return null;
        }
        // 是否还有更多 1是 0否
        //int hasMore = (int) jsonObject.get("has_more");
        JSONArray collect = awemeList.stream().map(item -> {
            JSONObject temp = new JSONObject();
            // item包含全部参数 awemeList[index]
            JSONObject object = (JSONObject) item;
            String previewTitle = (String) object.get("preview_title");
            JSONObject author = (JSONObject) object.get("author");
            String secUid = (String) author.get("sec_uid");
            String awemeId = (String) object.get("aweme_id");
            // 视频标题
            temp.put("previewTitle", previewTitle);
            // 作者ID
            temp.put("authorSecUid", secUid);
            // 视频ID
            temp.put("awemeId", awemeId);
            // 喜欢的创建时间
            temp.put("createTime",  object.get("create_time"));
            return temp;
        }).collect(Collectors.toCollection(JSONArray::new));

        JSONObject res = new JSONObject();
        res.put("aweme_list",collect);
        // 下一分页游标，时间戳
        res.put("max_cursor",jsonObject.get("max_cursor"));
        res.put("status_code",jsonObject.get("status_code"));
        return res;
    }
}
