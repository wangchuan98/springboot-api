package com.cc.service;

import com.cc.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourceService {
    //根据学员id查询正在进行的课程,可以
    public List<Course> queryForSid(List<String> ids);

    public List<Course> queryForCid(List<String> ids);
    public List<Course> queryMyStudentList(List<String> ids, String studentName);
}
