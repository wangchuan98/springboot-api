package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.utils.StringUtil;
import com.cc.entity.Course;
import com.cc.service.CourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2019-12-13
 */
@Api(tags = "课程接口")
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourceService courceService;
    @ApiOperation(value="查询课程", notes="查询课程接口")
    @RequestMapping(value = "/querycourse")
    private JsonResult querycourse( @RequestParam("studentId")String studentId) throws Exception  {
       if(StringUtil.isEmpty(studentId))
          return  JsonResult.error("暂无课程信息");
        ArrayList<String> ids= new ArrayList<String>();
        ids.add(studentId);
        List courseList=courceService.queryForSid(ids);
        if(courseList==null||courseList.size()==0)
            return  JsonResult.error("暂无课程信息");
        else {
            return  JsonResult.success("查询成功",courseList.get(0));
        }
    }
}
