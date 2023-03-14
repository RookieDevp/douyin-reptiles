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

    @GetMapping("/xbogus")
    public JSONObject getXbogus(String url,String userAgent){
        UrlBuilder builder = UrlBuilder.ofHttp(url, CharsetUtil.CHARSET_UTF_8);
        String queryStr = builder.getQueryStr();
        return reptilesService.getXbogus(queryStr, userAgent);
    }

    @GetMapping("/ttwid")
    public String getTTwid(){
        return reptilesService.getTTwid();
    }

    @GetMapping("/cookie")
    public String getCookie(){
        return reptilesService.getCookie();
    }

    @GetMapping("/getUserFavoriteList/{secUserId}")
    public JSONObject getUserFavoriteList(@PathVariable("secUserId") String secUserId
            , @RequestParam(required = false,defaultValue = "0") String maxCursor
            , @RequestParam(required = false,defaultValue = "0") String minCursor){
        return reptilesService.getUserFavoriteList(secUserId, maxCursor, minCursor);
    }
}
