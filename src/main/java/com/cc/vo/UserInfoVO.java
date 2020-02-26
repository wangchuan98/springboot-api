package com.cc.vo;

import com.cc.entity.Student;

/**
 * @program: springboot-api
 * @description: 用户信息视图对象
 * @author: wangchuan
 * @create: 2019-12-13
 */
public class UserInfoVO
{
    //用户身份
    private  Integer identify;
    //管理员id
    private  String adminId;
    //学员id
    private  String studentId;
    //教练id
    private  String coachId;
    //用户id
    private  String userId;
    //用户名称
    private  String  nickname;
    //登录用户名
    private  String userName;
    //密码
    private  String passWord;
    //用户头像地址
    private  String face;
    //用户性别
    private  Integer sex;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    private  String  sessionkey;

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getIdentify() {
        return identify;
    }

    public void setIdentify(Integer identify) {
        this.identify = identify;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }
}
