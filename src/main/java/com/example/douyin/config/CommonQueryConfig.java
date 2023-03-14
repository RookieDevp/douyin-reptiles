package com.example.douyin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
@Configuration
@ConfigurationProperties(prefix = "common.query")
public class CommonQueryConfig {

    private String aid;

    private String devicePlatform;

    private String param;

    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "CommonQueryConfig{" +
                "aid='" + aid + '\'' +
                ", devicePlatform='" + devicePlatform + '\'' +
                '}';
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }
}
