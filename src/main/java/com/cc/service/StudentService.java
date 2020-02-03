package com.cc.service;

import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.vo.StudentVO;

import java.util.List;
import java.util.Map;

public interface StudentService {

     String updatebyUserId(StudentVO studentVO,String userId);
     Student queryByStudentId(String studentId);
     List<Student> queryByParam(String name, String studentId, PageInfo pageInfo);
     Integer queryCountByParam(String name, String studentId);
     String insertStudent(StudentVO studentVO,String userId);
     boolean updatebyStudentId(StudentVO studentVO);
     void deletebyStudentId(List<String> idList);
}
