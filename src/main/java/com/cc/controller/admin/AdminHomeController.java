package com.cc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-09
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {
    @RequestMapping(value = "/addcoach")
    public String addCoach(HttpServletRequest request){
        return  "addcoach";
    }
    @RequestMapping(value = "/addcourse")
    public String addCourse(HttpServletRequest request){
        return  "addcourse";
    }
    @RequestMapping(value = "/coach/manage")
    public String coachManage(HttpServletRequest request){
        return  "coachmanage";
    }
    @RequestMapping(value = "/course/manage")
    public String courseManage(HttpServletRequest request){
        return  "coursemanage";
    }
    @RequestMapping(value = "/student/manage")
    public String studentManage(HttpServletRequest request){
        System.out.println("manage");
        return  "studentmanage";
    }
    @RequestMapping(value = "/addstudent")
    public String addstudent(HttpServletRequest request){
        return  "addstudent";
    }
    @RequestMapping(value = "/welcome")
    public String welcome(HttpServletRequest request){
        return  "welcome";
    }

    @RequestMapping(value = "/relogin")
    public String relogin(HttpServletRequest request){
        return  "relogin";
    }
}
