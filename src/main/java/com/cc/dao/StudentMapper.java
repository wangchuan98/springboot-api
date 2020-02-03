package com.cc.dao;

import com.cc.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentMapper {
     Student querybyUserid(String userid);
    void studentInsert(Student student);
    List<Student> queryByMap(@Param("map") Map<String,Object> map);
    List<Student> queryByStudentId(@Param("list") List<String> list);
    List<Student> queryByParams(@Param("map") Map<String,Object> map);
    Integer queryCountByParams(@Param("map") Map<String,Object> map);
    Integer updatebyStudentId(@Param("map") Map<String,Object> map);
    Integer enablebyStudentId(@Param("list") List<String> list,@Param("enable") Integer enable);
}
