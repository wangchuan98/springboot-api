package com.cc.entity;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-10
 */
public class Admin {
    private  String adminid;
    private  String nickname;
    private  String userid;
    private  Integer enable;

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
