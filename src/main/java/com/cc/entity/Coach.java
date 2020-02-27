package com.cc.entity;

import java.sql.Date;

/**
 * @program: springboot-api
 * @description: 教练实体类
 * @author: wangchuan
 * @create: 2019-12-13
 */
public class Coach {

    //教练id
    private  String coachId;
    //姓名
    private  String name;
    //性别
    private  Integer sex;
    //年龄
    private  Integer age;
    //电话
    private  String phone;
    //用户头像地址
    private  String face;
    //教龄
    private  Integer coachage;
    //教练车牌
    private  String coachcar;
    //用户id
    private  String userid;
    //失效标志
    private  Integer enable;
    //教学类型
    private  String teachtype;
    //创建人
    private  String creator;
    //创建时间
    private Date creattime;
    private  User user;
    //工作照片地址前缀
    //public  static  final  String PHOTO_PREFIX="http://localhost:8080/workphoto/";
     public  static  final  String PHOTO_PREFIX="https://www.wangchuan98.com/workphoto/";
    public  static  final  String PHOTO_SUFFIX=".jpg";
    //工作照片地址后缀

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTeachtype() {
        return teachtype;
    }

    public void setTeachtype(String teachtype) {
        this.teachtype = teachtype;
    }

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

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachid) {
        this.coachId = coachid;
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
