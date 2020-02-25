package com.cc.service;

import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.vo.CoachVO;
import com.cc.vo.StudentVO;

import java.util.List;
import java.util.Map;

public interface StudentService {

     //学员重启启用时的信息更新
     String updatebyUserId(StudentVO studentVO,String userId);
     //学员查询
     Student queryByStudentId(String studentId);
     //查询学员的教练信息
     List<CoachVO> queryMyCoach(String studentId);
     //分页查询学员列表
     List<Student> queryStudentList(String name, String studentId, PageInfo pageInfo);
     //查询学员总数
     Integer queryStudentCount(String name, String studentId);
    //学员新增
     String insertStudent(StudentVO studentVO,String userId);
     //学员信息更新
     boolean updatebyStudentId(StudentVO studentVO);
     //学员删除
     void deletebyStudentId(List<String> idList);
}
