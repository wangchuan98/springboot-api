package com.cc.service;

import com.cc.entity.Course;

import java.util.List;

public interface CourceService {
    //根据学员id查询正在进行的课程
    public List<Course> queryForSid(List<String> ids);
}
