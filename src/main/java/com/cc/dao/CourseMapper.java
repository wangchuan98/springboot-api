package com.cc.dao;

import com.cc.entity.Course;

import java.util.List;

public interface CourseMapper {
    List<Course> querybyStudentid(List<String> list);
}
