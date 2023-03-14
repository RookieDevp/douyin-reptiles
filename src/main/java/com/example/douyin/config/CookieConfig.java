package com.example.douyin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZZJ
 * @date: 2023/03/14
 * @desc:
 */
@Configuration
@ConfigurationProperties(prefix = "cookie")
public class CookieConfig {

    private String bdTicketGuardClientData;

    private String ttwid;

    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getTtwid() {
        return ttwid;
    }

    public void setTtwid(String ttwid) {
        this.ttwid = ttwid;
    }

    public String getBdTicketGuardClientData() {
        return bdTicketGuardClientData;
    }

    public void setBdTicketGuardClientData(String bdTicketGuardClientData) {
        this.bdTicketGuardClientData = bdTicketGuardClientData;
    }

    @Override
    public String toString() {
        return "CookieConfig{" +
                "bdTicketGuardClientData='" + bdTicketGuardClientData + '\'' +
                '}';
    }
}
