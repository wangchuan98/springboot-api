package com.cc.vo;

/**
 * @program: springboot-api
 * @description: 管理员端的视图VO
 * @author: wangchuan
 * @create: 2020-01-13
 */
public class StudentVO {
    //学员编号
    private  String studentId;
    //用户名
    private  String username;

    private  String password;
    //姓名
    private  String name;

    //性别 1代表男2代表女
    private  Integer sex;
    //年龄
    private  Integer age;
    //电话
    private  String tel;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
