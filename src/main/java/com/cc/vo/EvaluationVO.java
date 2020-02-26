package com.cc.vo;

import com.cc.entity.Coach;
import com.cc.entity.Student;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-14
 */
public class EvaluationVO {
    //评论id
    private  String evaluationId;
    //教练id
    private  String coachId;
    //学员id
    private  String studentId;
    //评论内容
    private  String content;
    //评论日期（格式化后）
    private  String formateDate;
    //学员姓名
    private  String studentName;
    //学员用户头像
    private  String studentFace;
    //评论日期
    private Timestamp date;

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormateDate() {
        return formateDate;
    }

    public void setFormateDate(String formateDate) {
        this.formateDate = formateDate;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentFace() {
        return studentFace;
    }

    public void setStudentFace(String studentFace) {
        this.studentFace = studentFace;
    }
}
