package com.cc.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @program: springboot-api
 * @description:教练评价实体类
 * @author: wangchuan
 * @create: 2020-02-13
 */
public class Evaluation {
    private  String evaluationId;
    private  String coachId;
    private  String studentId;
    private  String content;
    private Timestamp date;
    private  Coach coach;
    private  Student student;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

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
}
