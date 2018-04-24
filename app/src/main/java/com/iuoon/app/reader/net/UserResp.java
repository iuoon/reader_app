package com.iuoon.app.reader.net;

import com.iuoon.app.reader.model.XUser;

/**
 * Created by iuoon on 2018/3/20.
 */

public class UserResp extends TransVo{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User{
        private String strMobile;
        private String strPwd;
        private String strUserName;
        private String userId;
        private String token;

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
    }
}
