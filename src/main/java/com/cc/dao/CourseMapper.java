package com.cc.dao;

import com.cc.entity.Course;

public interface CourseMapper {
    Course querybyStudentid(String studentid);
}
