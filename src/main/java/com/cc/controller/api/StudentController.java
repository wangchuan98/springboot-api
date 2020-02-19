package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.utils.StringUtil;
import com.cc.entity.Course;
import com.cc.entity.Student;
import com.cc.service.StudentService;
import com.cc.vo.CoachVO;
import com.cc.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-12
 */
@Controller
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value="/querymycoach")
    @ResponseBody
    public JsonResult queryforstatus(@RequestParam("studentId") String studentId) {
        if (StringUtil.isEmpty(studentId))
            return JsonResult.error("当前学员信息为空");
        List<CoachVO> list = studentService.queryMyCoach(studentId);
        return  JsonResult.success("查询成功",list);

    }


    @RequestMapping(value="/querystudentdetail")
    @ResponseBody
    public JsonResult queryStudentDetail(@RequestParam("studentId") String studentId) {
        if (StringUtil.isEmpty(studentId))
            return JsonResult.error("当前学员信息为空");
        Student student = studentService.queryByStudentId(studentId);
        return  JsonResult.success("查询成功",student);

    }

}
