package com.cc.service.impl;

import com.cc.dao.CourseMapper;
import com.cc.entity.Course;
import com.cc.service.CourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Course> courseList=courseMapper.queryByStudentId(ids);
        return courseList;
    }

    @Override
    public List<Course> queryForCid(List<String> ids) {
        HashMap<String,Object> param=new HashMap<>();
        List<Course> courseList=courseMapper.queryByCoachId(ids,param);
        return courseList;
    }

    @Override
    public List<Course> queryMyStudentList(List<String> ids, String studentName) {
        HashMap<String,Object> param=new HashMap<>();
        param.put("studentName",studentName);
        List<Course> courseList=courseMapper.queryByCoachId(ids,param);
        return courseList;
    }
}
