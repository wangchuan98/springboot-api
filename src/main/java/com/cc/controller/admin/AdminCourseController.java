package com.cc.controller.admin;

import com.cc.common.JsonResult;
import com.cc.common.utils.ObjectUtil;
import com.cc.entity.Coach;
import com.cc.entity.Course;
import com.cc.entity.PageInfo;
import com.cc.entity.User;
import com.cc.service.CourceService;
import com.cc.vo.CoachVO;
import com.cc.vo.CourseVO;
import com.cc.vo.TableResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-24
 */
@Controller
@RequestMapping("/admin/course")
public class AdminCourseController {

    private final String[]  COURSE_SAVECANNULL = {"courseId", "courseStatus","creattime","studentName","coachName"};
    @Autowired
    private CourceService courceService;

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/save")
    @ResponseBody
    public JsonResult save(@RequestBody  CourseVO courseVO, HttpServletRequest request) throws Exception {
        HttpSession session=request.getSession();
        if(session!=null) {
            String creator =String.valueOf(session.getAttribute("userId"));
            courseVO.setCreator(creator);
        }
        if (ObjectUtil.objectIsEmpty(courseVO, COURSE_SAVECANNULL))
            return JsonResult.error("必填参数存在空值");
        courceService.saveCourse(courseVO);
        return JsonResult.success("添加成功");
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResult update(@RequestBody List<String> idList,HttpServletRequest request) throws Exception {
        Integer status=Integer.valueOf(request.getParameter("status"));
        if(idList==null||idList.size()<0){
            return  JsonResult.error("请求参数为空");
        }
        if(status<=0||status>3){
            return  JsonResult.error("课程状态错误");
        }
        courceService.updateCourse(idList,status);
        return JsonResult.success("修改成功");
    }

    @RequestMapping(value = "/courselist")
    @ResponseBody
    public TableResultVO<CourseVO> adminCourseList(HttpServletRequest request){

        //获取查询条件
        HashMap<String,Object> where=new HashMap<>();
        where.put("studentName",request.getParameter("studentName"));
        where.put("coachName",request.getParameter("coachName"));
        where.put("studentId",request.getParameter("studentId"));
        where.put("coachId",request.getParameter("coachId"));
        where.put("coursetype",request.getParameter("coursetype"));
        where.put("status",request.getParameter("courseStatus"));
        Integer pageSize=Integer.valueOf(request.getParameter("pageSize"));
        Integer pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
        PageInfo pageInfo=new PageInfo(pageNumber,pageSize);
        List<CourseVO> list=courceService.queryCoachList(where,pageInfo);
        //查询总数
        Integer count=courceService.queryCountCoach(where);
        TableResultVO<CourseVO> resultVO=new TableResultVO<>();
        resultVO.setRows(list);
        resultVO.setTotal(count);
        return  resultVO;
    }




}
