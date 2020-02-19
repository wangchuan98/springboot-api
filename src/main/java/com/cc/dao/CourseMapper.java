package com.cc.dao;

import com.cc.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    //根据学员id查询课程信息
    List<Course> queryByStudentId(@Param("studentList") List<String> studentList);
    //根据教练id和其他条件查询课程信息
    List<Course> queryByCoachId(@Param("coachList") List<String> coachList,@Param("param")Map<String,Object> param );
    //查询教练的默认课程（休假课程）
    String queryDefaultCourse(String coachId);

}
