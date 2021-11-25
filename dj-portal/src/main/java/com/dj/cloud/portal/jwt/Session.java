package com.dj.cloud.portal.jwt;

import java.time.LocalDateTime;
import java.util.Date;

public class Session {

    LocalDateTime expireDateTime = LocalDateTime.now();
    private Integer userId;
    private String userName;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(LocalDateTime expireDateTime) {
        this.expireDateTime = expireDateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
