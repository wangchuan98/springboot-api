package com.cc.entity;

/**
 * @program: springboot-api
 * @description: 教练实体类
 * @author: wangchuan
 * @create: 2019-12-13
 */
public class Coach {


    private  String coachid;
    private  String name;
    private  Integer sex;
    private  Integer age;
    private  String phone;
    private  String face;
    private  Integer coachage;
    private  String coachcar;
    private  String userid;
    private  Integer enable;

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoachid() {
        return coachid;
    }

    public void setCoachid(String coachid) {
        this.coachid = coachid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getCoachage() {
        return coachage;
    }

    public void setCoachage(Integer coachage) {
        this.coachage = coachage;
    }

    public String getCoachcar() {
        return coachcar;
    }

    public void setCoachcar(String coachcar) {
        this.coachcar = coachcar;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
