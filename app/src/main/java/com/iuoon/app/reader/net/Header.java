package com.iuoon.app.reader.net;

/**
 * Created by iuoon on 2018/3/29.
 */

public class Header {
    private String userId;
    private String token;
    private String strPlatform;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStrPlatform() {
        return strPlatform;
    }

    public void setStrPlatform(String strPlatform) {
        this.strPlatform = strPlatform;
    }
}
