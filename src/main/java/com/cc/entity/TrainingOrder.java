package com.cc.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.sql.Time;

/**
 * @program: springboot-api
 * @description: 预约订单实体类
 * @author: wangchuan
 * @create: 2019-12-20
 */
@ApiModel(value="预约订单对象", description="预约订单对象")
public class TrainingOrder {

    @ApiModelProperty(hidden=true)
    private  String orderid;
    //订单日期
    @ApiModelProperty(value="订单日期", name="date", example="2019-12-22", required=true)
    private  Date date;
    //开始时间
    @ApiModelProperty(value="开始时间", name="begintime", example="8:00:00", required=true)
    private  Time begintime;
    //结束时间
    @ApiModelProperty(value="结束时间", name="endtime", example="9:00:00", required=true)
    private  Time endtime;
    //学员id
    @ApiModelProperty(value="学员id", name="studentid", example="s002", required=true)
    private  String studentid;
    //教练id
    @ApiModelProperty(value="教练id", name="coachid", example="c003", required=true)
    private  String coachid;
    //坐标
    @ApiModelProperty(value="坐标", name="locationid", example="0", required=true)
    private  String locationid;
    //状态 状态:1未预约2已预约3调休
    @ApiModelProperty(hidden=true)
    private  Integer status;

    private  Coach coach;

    private  Student student;

    private  Course course;

    //Getter和Setter方法
    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBegintime() {
        return begintime;
    }

    public void setBegintime(Time begintime) {
        this.begintime = begintime;
    }

    public Time getEndtime() {
        return endtime;
    }

    public void setEndtime(Time endtime) {
        this.endtime = endtime;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCoachid() {
        return coachid;
    }

    public void setCoachid(String coachid) {
        this.coachid = coachid;
    }

    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
