package com.cc.entity;

/**
 * @program: springboot-api
 * @description: 学员实体类
 * @author: wangchuan
 * @create: 2019-12-12
 */
public class Student {
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", face='" + face + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }

    //主键
    private  String studentId;
    //学员姓名
    private  String name;
    //年龄
    private  Integer age;
    //性别 1代表男0代表女
    private  Integer sex;
    //手机号
    private  String phone;
    //头像地址
    private  String face;
    //userid
    private  String userid;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
