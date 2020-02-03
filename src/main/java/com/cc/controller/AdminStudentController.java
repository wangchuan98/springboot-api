package com.cc.controller;

import com.cc.common.JsonResult;
import com.cc.common.utils.ObjectUtil;
import com.cc.common.utils.StringUtil;
import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.entity.User;
import com.cc.service.CourceService;
import com.cc.service.StudentService;
import com.cc.service.UserService;
import com.cc.vo.StudentVO;
import com.cc.vo.TableResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-12
 */
@Controller
@RequestMapping("/admin/student")
public class AdminStudentController {



    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    CourceService courceService;


    //非空检查时，可以为空的参数
     private final  String[] STUDENT_SAVECANNULL={"studentId","tel"};
    private final  String[] STUDENT_UPDATACANNULL={"studentId","tel","username","password"};
     @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/save")
    @ResponseBody
    public JsonResult save(@RequestBody StudentVO vo) throws Exception {
        //校验空值
        if(ObjectUtil.objectIsEmpty(vo,STUDENT_SAVECANNULL))
            return  JsonResult.error("必填参数存在空值");
        //校验数据格式
         JsonResult validate=validateStudent(vo);
         if(validate!=null)
             return  validate;
         //查询是否有相同的用户名
         User result=userService.isExsitUsername(vo.getUsername());
        if(result!=null){
            //如果启用状态的，返回用户名已存在
            if(result.getEnable()==0)
                return  JsonResult.error("用户名已存在");
            else
            {
                //如果是注销的，就update原先的数据
                userService.updateByUserId(result.getUserId(),vo.getPassword());
                String studentId=studentService.updatebyUserId(vo,result.getUserId());
                return   JsonResult.success("学员添加成功",studentId);
            }
        }


        String userId=userService.insertUser(vo.getUsername(),vo.getPassword(),User.STUDENT);
        String studentId=studentService.insertStudent(vo,userId);
        return   JsonResult.success("学员添加成功",studentId);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResult update(@RequestBody StudentVO vo,HttpServletRequest request) throws Exception {
         //校验空值
        if(ObjectUtil.objectIsEmpty(vo,STUDENT_UPDATACANNULL))
            return  JsonResult.error("必填参数存在空值");
        //校验数据格式
        JsonResult validate=validateStudent(vo);
        if(validate!=null)
            return  validate;
        String studentId=request.getParameter("studentId");
        if(StringUtil.isEmpty(studentId))
            return  JsonResult.error("修改失败");
        vo.setStudentId(studentId);
        Boolean flag=studentService.updatebyStudentId(vo);
        if(flag){
            return JsonResult.success("修改成功",null);
        }else {
            return  JsonResult.error("修改失败");
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResult delete(@RequestBody List<String> idList) throws Exception {
            if(idList.size()<=0)
            {
                return  JsonResult.error("请求参数为空");
            }
            //先查询是否有进行的课程
              List<Course> coutseList=courceService.queryForSid(idList);
            if(coutseList.size()>0){
                String msg="学员：";
                for(Course item:coutseList){
                    msg=msg+item.getStudentid()+" ";
                }
                msg=msg+"仍存在进行的课程";
                return  JsonResult.error(msg);
            }
            //将启用标志改为失效，也就是1
            studentService.deletebyStudentId(idList);
            return  JsonResult.success("删除成功",null);


    }


    @RequestMapping(value = "/studentlist")
    @ResponseBody
    public TableResultVO<Student> adminStudentlist(HttpServletRequest request){

         String name=request.getParameter("name");
         String studentId=request.getParameter("studentId");
         Integer pageSize=Integer.valueOf(request.getParameter("pageSize"));
         Integer pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
         PageInfo pageInfo=new PageInfo(pageNumber,pageSize);
         List<Student> list=studentService.queryByParam(name,studentId,pageInfo);
         //查询总数
        Integer count=studentService.queryCountByParam(name,studentId);
        TableResultVO<Student> resultVO=new TableResultVO<>();
        resultVO.setRows(list);
        resultVO.setTotal(count);
        return  resultVO;
    }

    @RequestMapping(value = "/studentdetail")
    public String studentDetail(HttpServletRequest request){
       this.setStudentInfo(request);
        return "studentdetail";
    }

    @RequestMapping(value = "/studentedit")
    public String studentEdit(HttpServletRequest request){
        this.setStudentInfo(request);
        return "studentedit";
    }

    @RequestMapping(value = "/manage")
    public String manage(HttpServletRequest request){
        System.out.println("manage");
        return  "studentmanage";
    }

    private  JsonResult validateStudent(StudentVO vo){

         JsonResult result=null;

         //String regex_age="[+\\d]?\\d{0,3}";
        //手机号可以不输入
        String tel=vo.getTel();
        String regex_tel="[+\\d]?\\d{0,20}";
         if(tel!=null){
             if(!tel.matches(regex_tel))
                result=JsonResult.error("手机格式错误");
         }
         return  result;
    }

    private  void setStudentInfo(HttpServletRequest request){
        String studentId=request.getParameter("studentId");
        Student student=studentService.queryByStudentId(studentId);
        if(student!=null) {
            request.setAttribute("studentId", student.getStudentId());
            request.setAttribute("name", student.getName());
            request.setAttribute("sex", student.getSex());
            request.setAttribute("age", student.getAge());
            request.setAttribute("phone", student.getPhone());
        }
    }









}
