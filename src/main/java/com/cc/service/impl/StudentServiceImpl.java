package com.cc.service.impl;

import com.cc.common.exception.StudentDeleteException;
import com.cc.common.utils.FlowCodeUtil;
import com.cc.dao.StudentMapper;
import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.service.StudentService;
import com.cc.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot-api
 * @description: 学员的服务类
 * @author: wangchuan
 * @create: 2019-12-13
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private FlowCodeUtil flowCodeUtil;

    @Override
    public Student querybyUserid(String userid) {
        Student student = studentMapper.querybyUserid(userid);
        return student;
    }

    @Override
    public List<Student> queryByMap(Map map) {
        List<Student> list = studentMapper.queryByMap(map);
        return list;
    }

    @Override
    public List<Student> queryByParam(String name, String studentId, PageInfo pageInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("name", name);
        map.put("currIndex", pageInfo.getCurrIndex());
        map.put("pageSize", pageInfo.getPageSize());
        List<Student> list = studentMapper.queryByParams(map);
        return list;
    }

    @Override
    public Integer queryCountByParam(String name, String studentId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("name", name);
        Integer count = studentMapper.queryCountByParams(map);
        return count;
    }

    @Override
    public String insertStudent(StudentVO vo, String userId) {
        //创建student
        Student student = new Student();
        String studentId = flowCodeUtil.generateCode("S");
        student.setAge(vo.getAge());
        student.setName(vo.getName());
        student.setPhone(vo.getTel());
        student.setSex(vo.getSex());
        student.setUserid(userId);
        student.setStudentId(studentId);
        studentMapper.studentInsert(student);
        return student.getStudentId();
    }

    @Override
    public boolean updatebyStudentId(StudentVO studentVO) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentId", studentVO.getStudentId());
        map.put("sex", studentVO.getSex());
        map.put("name", studentVO.getName());
        map.put("phone", studentVO.getTel());
        map.put("age", studentVO.getAge());
        Integer count = studentMapper.updatebyStudentId(map);
        if (count > 0)
            return true;
        else
            return false;
    }

    @Override
    public void deletebyStudentId(List<String> idList) {

        Integer count=Integer.valueOf(0);
        try {
            count = studentMapper.deletebyStudentId(idList);
        } catch (Exception e) {
            StudentDeleteException.fail("数据已引用，删除失败");
        }
        //如果idlist的数量和删除数量不相等
        if(idList.size()!=count)
        StudentDeleteException.fail("删除失败");


    }
}
