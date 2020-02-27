package com.cc.service.impl;

import com.cc.common.exception.CommonException;
import com.cc.common.utils.SnowflakeIdWorker;
import com.cc.dao.CourseMapper;
import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.service.CourceService;
import com.cc.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    public void saveCourse(CourseVO courseVO) {
        //查询学员有没有正在进行的课程
        ArrayList<String> ids=new ArrayList<>();
        ids.add(courseVO.getStudentId());
        List<Course> courseList=courseMapper.queryByStudentId(ids);
        if(courseList!=null&&courseList.size()>0)
            throw new CommonException("学员已经有正在进行的课程！");
        Date creattime = new Date(System.currentTimeMillis());
        //创建coach
        Course course = new Course();
        //生成courseId
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(5, 1);
        long id = snowflakeIdWorker.nextId();
        course.setCourseid(String.valueOf(id));
        course.setCoachid(courseVO.getCoachId());
        course.setStudentid(courseVO.getStudentId());
        course.setStatus(Integer.valueOf(2));
        course.setSubject(courseVO.getSubject());
        course.setLicensetype(courseVO.getLicensetype());
        course.setCoursetype(courseVO.getCoursetype());
        course.setCreator(courseVO.getCreator());
        course.setCreattime(creattime);
        courseMapper.insertCourse(course);
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

    @Override
    public List<CourseVO> queryCoachList(Map<String, Object> where, PageInfo pageInfo) {
        where.put("currIndex", pageInfo.getCurrIndex());
        where.put("pageSize", pageInfo.getPageSize());
        List<Course> list = courseMapper.queryCourseList(where);
        ArrayList<CourseVO> voList=new ArrayList<>();
        //转化成VO
        for(Course course:list){
            CourseVO vo=new CourseVO();
            vo.setCourseId(course.getCourseid());
            vo.setCoachId(course.getCoachid());
            vo.setCoachName(course.getCoach().getName());
            vo.setStudentId(course.getStudentid());
            vo.setStudentName(course.getStudent().getName());
            vo.setCoursetype(course.getCoursetype());
            vo.setCourseStatus(course.getStatus());
            vo.setLicensetype(course.getLicensetype());
            vo.setSubject(course.getSubject());
            voList.add(vo);
        }
        return  voList;
    }

    @Override
    public Integer queryCountCoach(Map<String, Object> where) {
        return courseMapper.queryCount(where);
    }

    @Override
    public void updateCourse(List<String> ids, Integer status) {
        courseMapper.updateCourse(ids,status);
    }
}
