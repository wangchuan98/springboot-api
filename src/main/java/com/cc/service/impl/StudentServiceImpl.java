package com.cc.service.impl;

import com.cc.common.enums.Status;
import com.cc.common.exception.StudentDeleteException;
import com.cc.common.utils.FlowCodeUtil;
import com.cc.dao.CourseMapper;
import com.cc.dao.StudentMapper;
import com.cc.dao.UserMapper;
import com.cc.entity.Coach;
import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.service.StudentService;
import com.cc.vo.CoachVO;
import com.cc.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.util.*;

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
    private  CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FlowCodeUtil flowCodeUtil;



    /**
     * 查询单个学员信息
     * @param studentId
     * @return
     */
    @Override
    public Student queryByStudentId(String studentId) {
        List<String> ids=new ArrayList<>();
        ids.add(studentId);
        List<Student> list = studentMapper.queryByStudentId(ids);
        if(list!=null&&list.size()>0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 查询学员的教练信息
     * @param studentId
     * @return
     */
    @Override
    public  List<CoachVO> queryMyCoach(String studentId) {
        List<String> ids=new ArrayList<>();
        ids.add(studentId);
        List<Course> courseList=courseMapper.queryByStudentId(ids);
        List<CoachVO> coachVOList=new ArrayList<>();
        for(Course course:courseList) {
            Coach coach = course.getCoach();
            CoachVO coachVO = new CoachVO();
            coachVO.setCoachId(coach.getCoachId());
            coachVO.setName(coach.getName());
            coachVO.setCoachcar(coach.getCoachcar());
            coachVO.setFace(coach.getFace());
            coachVO.setTel(coach.getPhone());
            coachVO.setAge(coach.getAge());
            coachVO.setTeachtype(coach.getTeachtype());
            coachVO.setWorkphoto(Coach.PHOTO_PREFIX+coach.getCoachId()+Coach.PHOTO_SUFFIX);
            coachVOList.add(coachVO);
        }
        return  coachVOList;
    }

    /**
     * 分页查询学员列表
     * @param name
     * @param studentId
     * @param pageInfo
     * @return
     */
    @Override
    public List<Student> queryStudentList(String name, String studentId, PageInfo pageInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("name", name);
        map.put("currIndex", pageInfo.getCurrIndex());
        map.put("pageSize", pageInfo.getPageSize());
        List<Student> list = studentMapper.queryByParams(map);
        return list;
    }

    /**
     * 查询符合查询条件的学员总数
     * @param name
     * @param studentId
     * @return
     */
    @Override
    public Integer queryStudentCount(String name, String studentId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        map.put("name", name);
        Integer count = studentMapper.queryCountByParams(map);
        return count;
    }

    /**
     * 学员新增
     * @param vo
     * @param userId
     * @return
     */
    @Override
    public String insertStudent(StudentVO vo, String userId) {
        Date  creattime=new Date(System.currentTimeMillis());
        //创建student
        Student student = new Student();
        String studentId = flowCodeUtil.generateCode("S");
        student.setAge(vo.getAge());
        student.setName(vo.getName());
        student.setPhone(vo.getTel());
        student.setSex(vo.getSex());
        student.setCreator(vo.getCreator());
        student.setCreattime(creattime);
        student.setUserid(userId);
        student.setStudentId(studentId);
        studentMapper.studentInsert(student);
        return student.getStudentId();
    }

    /**
     *  情景：学员有过删除记录，重新启用时
     *  功能：更新学员的信息
     * @param studentVO
     * @param userId
     * @return
     */
    @Override
    public String updatebyUserId(StudentVO studentVO, String userId) {
        //先根据userId在查出studentId
        Student student=studentMapper.querybyUserid(userId);
        String studentId;
        if(student!=null) {
            studentId=student.getStudentId();
            HashMap<String, Object> map = new HashMap<>();
            map.put("studentId", studentId);
            map.put("sex", studentVO.getSex());
            map.put("name", studentVO.getName());
            map.put("phone", studentVO.getTel());
            map.put("age", studentVO.getAge());
            map.put("enable", Integer.valueOf(0));
            studentMapper.updatebyStudentId(map);
        }else{
            studentId=this.insertStudent(studentVO,userId);
        }
        return  studentId;
    }


    /**
     * 单个学员信息修改
     * @param studentVO
     * @return
     */
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

    /**
     * 批量修改学员的启用状态
     * @param idList
     */
    @Override
    public void deletebyStudentId(List<String> idList) {
        //更具studentId 查出userid集合，把userid的启用状态也置为1
        List<Student> resultList=studentMapper.queryByStudentId(idList);
        List<String> userIdList=new ArrayList<>();
        for(Student item:resultList){
            userIdList.add(item.getUserid());
        }
        studentMapper.enablebyStudentId(idList,Integer.valueOf(1));
        userMapper.enablebyUserId(userIdList,Integer.valueOf(1));
    }

//    @Override
//    public void deletebyStudentId(List<String> idList) {
//        Integer count=Integer.valueOf(0);
//        try {
//            count = studentMapper.deletebyStudentId(idList);
//        } catch (Exception e) {
//            StudentDeleteException.fail("数据已引用，删除失败");
//        }
//        //如果idlist的数量和删除数量不相等
//        if(idList.size()!=count)
//        StudentDeleteException.fail("删除失败");
//    }


}
