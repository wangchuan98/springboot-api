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
    private  Integer identify;
    private  String adminId;
    private  String studentId;
    private  String coachId;
    private  String  nickname;

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
