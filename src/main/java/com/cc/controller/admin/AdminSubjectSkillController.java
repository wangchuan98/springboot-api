package com.cc.controller.admin;

import com.cc.common.JsonResult;
import com.cc.common.utils.StringUtil;
import com.cc.entity.PageInfo;
import com.cc.entity.Student;
import com.cc.entity.SubjectSkill;
import com.cc.service.SubjectSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-17
 */
@Controller
@RequestMapping("/admin")
public class AdminSubjectSkillController {

    private  final  Integer DEFAULT_SIZE=5;

    @Autowired
    SubjectSkillService subjectSkillService;
    @RequestMapping("/subjectskill")
    public String subjectskill(HttpServletRequest request){
        //查询下科目二和科目三的技巧总数
        Map<String,Integer> map=subjectSkillService.querySkillCount();
        Integer subject2=map.get("subject2");
        Integer subject3=map.get("subject3");
        request.setAttribute("subject2",subject2);
        request.setAttribute("subject3",subject3);
        return "subjectskill";
    }

    @RequestMapping("/skilllist")
    public String skilllist(HttpServletRequest request){

        Integer pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
        Integer category=Integer.valueOf(request.getParameter("category"));
        PageInfo pageInfo=new PageInfo(pageNumber,DEFAULT_SIZE);
        List<SubjectSkill> list=subjectSkillService.queryByParam(category,pageInfo);
        for(SubjectSkill item:list){
            String content=item.getContent();
            //列表中内容只显示50字符
            String limit=item.getContent().substring(0,content.length()<200?content.length():200);
            item.setContent(limit);
        }
        request.setAttribute("subjectList",list);
        return "skilllist";
    }

    @RequestMapping("/skilldelete")
    @ResponseBody
    public JsonResult skilldelete(HttpServletRequest request){

        String  id=request.getParameter("id");
        if(StringUtil.isEmpty(id))
            return  JsonResult.error("删除失败");
        subjectSkillService.deletebyId(id);
        return JsonResult.success("删除成功",null);
    }

}
