package com.example.douyin.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
public interface IReptilesService {

    public JSONObject getXbogus(String url, String userAgent);

    public String getTTwid();

    public String getCookie();

    public String refreshCookie();

    public JSONObject getUserFavoriteList(String secUserId, String maxCursor, String minCursor);
}
