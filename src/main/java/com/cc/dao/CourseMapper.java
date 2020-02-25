package com.cc.dao;

import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.vo.CourseVO;
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
    //新增课程
    void insertCourse(Course course);
    //分页查询课程信息
    List<Course> queryCourseList(@Param("map") Map<String,Object> map);
    //查询课程信息总数
    Integer queryCount(@Param("map") Map<String,Object> map);
    //批量修改课程状态
    void updateCourse(@Param("courseList") List<String> courseList,@Param("status")Integer status);


}
