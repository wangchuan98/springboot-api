package com.cc.service.impl;

import com.cc.dao.CourseMapper;
import com.cc.entity.Course;
import com.cc.service.CourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2019-12-13
 */
@Service
public class CourceServiceImpl implements CourceService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> queryForSid( List<String> ids) {
        List<Course> courseList=courseMapper.querybyStudentid(ids);
        return courseList;
    }
}
