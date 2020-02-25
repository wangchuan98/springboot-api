package com.cc.service;

import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.vo.CourseVO;

import java.util.List;
import java.util.Map;

public interface CourceService {
    //根据学员id查询正在进行的课程
     List<Course> queryForSid(List<String> ids);
     //课程新增
     void  saveCourse(CourseVO courseVO);
     //根据教练id查询正在进行的课程
     List<Course> queryForCid(List<String> ids);
     //获取教练的学员列表
     List<Course> queryMyStudentList(List<String> ids, String studentName);
     //分页查询课程
    List<CourseVO> queryCoachList(Map<String,Object> where, PageInfo pageInfo);
    //查询课程总数
    Integer queryCountCoach(Map<String,Object> where);

    void updateCourse(List<String> ids,Integer status);
}
