package com.example.douyin.controller;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.douyin.service.Impl.ReptilesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
@RestController
@RequestMapping("/reptiles")
public class ReptilesController {

    @Autowired
    private ReptilesServiceImpl reptilesService;

    @GetMapping("/health")
    public String test(){
        return "ok";
    }

    /***
     * @author ZZJ
     * @date 2023/3/14
     * @param url
     * @param userAgent
     * @return com.alibaba.fastjson.JSONObject
     * @desc  根据url、userAgent 生成X-Bogus
     */
    @GetMapping("/xbogus")
    public JSONObject getXbogus(String url,String userAgent){
        UrlBuilder builder = UrlBuilder.ofHttp(url, CharsetUtil.CHARSET_UTF_8);
        String queryStr = builder.getQueryStr();
        return reptilesService.getXbogus(queryStr, userAgent);
    }

    /***
     * @author ZZJ
     * @date 2023/3/14
     * @return java.lang.String
     * @desc  利用字节中间接口注册ttwid
     */
    @GetMapping("/ttwid")
    public String getTTwid(){
        return reptilesService.getTTwid();
    }

    /***
     * @author ZZJ
     * @date 2023/3/14
     * @return java.lang.String
     * @desc  获取cookie，参数：ttwid=xxx;bd_ticket_guard_client_data=xxx
     */
    @GetMapping("/cookie")
    public String getCookie(){
        return reptilesService.getCookie();
    }

    /***
     * @author ZZJ
     * @date 2023/3/14
     * @param secUserId
     * @param maxCursor
     * @param minCursor
     * @return com.alibaba.fastjson.JSONObject
     * @desc  根据secUserId获取喜欢列表信息  example：127.0.0.1:8088/reptiles/getUserFavoriteList/MS4wLjABAAAApmi-USaKOChKt5pX20FjhjJMcH2bFRfh04i2aP-zVlI?maxCursor=1574048283000
     */
    @GetMapping("/getUserFavoriteList/{secUserId}")
    public JSONObject getUserFavoriteList(@PathVariable("secUserId") String secUserId
            , @RequestParam(required = false,defaultValue = "0") String maxCursor
            , @RequestParam(required = false,defaultValue = "0") String minCursor){
        return reptilesService.getUserFavoriteList(secUserId, maxCursor, minCursor);
    }
}
