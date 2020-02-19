package com.cc.vo;

/**
 * @program: springboot-api
 * @description: 关于教练的前台数据VO
 * @author: wangchuan
 * @create: 2020-02-09
 */
public class CoachVO {
    //教练的ID
    private  String coachId;
    //登陆用户名
    private  String username;
    //登陆密码
    private  String password;
    //教练姓名
    private  String name;
    //教练性别
    private  Integer sex;
    //教练年龄
    private  Integer age;
    //教练电话
    private  String tel;

    private  String face;
    //教练车牌号
    private  String coachcar;
    //教学类型
    private  String teachtype;

    private  String workphoto;

    public String getWorkphoto() {
        return workphoto;
    }

    public void setWorkphoto(String workphoto) {
        this.workphoto = workphoto;
    }



    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
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

    public String getCoachcar() {
        return coachcar;
    }

    public void setCoachcar(String coachcar) {
        this.coachcar = coachcar;
    }

    public String getTeachtype() {
        return teachtype;
    }

    public void setTeachtype(String teachtype) {
        this.teachtype = teachtype;
    }
}
