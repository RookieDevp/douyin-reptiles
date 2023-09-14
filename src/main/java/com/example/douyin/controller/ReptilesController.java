package com.example.douyin.controller;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.douyin.domain.AjaxResult;
import com.example.douyin.service.Impl.ReptilesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public AjaxResult getXbogus(String url, String userAgent){
        UrlBuilder builder = UrlBuilder.ofHttp(url, CharsetUtil.CHARSET_UTF_8);
        String queryStr = builder.getQueryStr();
        String xbogus = reptilesService.getXbogus(queryStr, userAgent);
        if (StrUtil.isEmpty(xbogus)) {
            return AjaxResult.error();
        }
        return AjaxResult.success("操作成功",xbogus);
    }

    /***
     * @author ZZJ
     * @date 2023/3/14
     * @return java.lang.String
     * @desc  利用字节中间接口注册ttwid
     */
    @GetMapping("/ttwid")
    public AjaxResult getTTwid(){
        String tTwid = reptilesService.getTTwid();
        if (StrUtil.isEmpty(tTwid)) {
            return AjaxResult.error();
        }
        return AjaxResult.success(tTwid);
    }

    /***
     * @author ZZJ
     * @date 2023/3/14
     * @return java.lang.String
     * @desc  获取cookie，参数：ttwid=xxx;bd_ticket_guard_client_data=xxx
     */
    @GetMapping("/cookie")
    public AjaxResult getCookie(){
        String cookie = reptilesService.getCookie();
        if (StrUtil.isEmpty(cookie)) {
            return AjaxResult.error();
        }
        return AjaxResult.success(cookie);
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
    public AjaxResult getUserFavoriteList(@PathVariable("secUserId") String secUserId
            , @RequestParam(required = false,defaultValue = "0") String maxCursor
            , @RequestParam(required = false,defaultValue = "0") String minCursor){
        JSONObject favoriteList = reptilesService.getUserFavoriteList(secUserId, maxCursor, minCursor);
        if (ObjectUtil.isEmpty(favoriteList)){
            return AjaxResult.error();
        }
        return AjaxResult.success(favoriteList);
    }

    /***
     * @author ZZJ
     * @date 2023/3/19
     * @param secUserId
     * @param maxCursor
     * @param minCursor
     * @return com.example.douyin.domain.AjaxResult
     * @desc  根据secUserId获取喜欢列表信息（IP代理）
     */
    @GetMapping("/getUserFavoriteListV2/{secUserId}")
    public AjaxResult getUserFavoriteListV2(@PathVariable("secUserId") String secUserId
            , @RequestParam(required = false,defaultValue = "0") String maxCursor
            , @RequestParam(required = false,defaultValue = "0") String minCursor){
        JSONObject favoriteList = reptilesService.getUserFavoriteListV2(secUserId, maxCursor, minCursor);
        if (ObjectUtil.isEmpty(favoriteList)){
            return AjaxResult.error();
        }
        return AjaxResult.success(favoriteList);
    }
}
