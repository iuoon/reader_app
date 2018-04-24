package com.iuoon.app.reader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iuoon.app.reader.net.Header;

/**
 * Created by iuoon on 2018/3/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XUser {

    private String strMobile;
    private String strPwd;
    private String strUserName;
    private Header header;

    public String getStrMobile() {
        return strMobile;
    }

    public void setStrMobile(String strMobile) {
        this.strMobile = strMobile;
    }

    public String getStrPwd() {
        return strPwd;
    }

    public void setStrPwd(String strPwd) {
        this.strPwd = strPwd;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

}
