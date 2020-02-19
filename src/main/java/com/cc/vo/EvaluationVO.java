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
    private  String evaluationId;
    private  String coachId;
    private  String studentId;
    private  String content;
    private  String formateDate;
    private  String studentName;
    private  String studentFace;
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
