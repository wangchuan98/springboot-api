package com.cc.dao;

import com.cc.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentMapper {
     //根据userid查询
     Student querybyUserid(String userid);
    //插入
    void studentInsert(Student student);
    //根据studentId查询
    List<Student> queryByStudentId(@Param("list") List<String> list);
    //根据查询条件分页查询
    List<Student> queryByParams(@Param("map") Map<String,Object> map);
    //分页查询的总数
    Integer queryCountByParams(@Param("map") Map<String,Object> map);
    //更新学员信息
    Integer updatebyStudentId(@Param("map") Map<String,Object> map);
    //学员失效
    Integer enablebyStudentId(@Param("list") List<String> list,@Param("enable") Integer enable);
}
