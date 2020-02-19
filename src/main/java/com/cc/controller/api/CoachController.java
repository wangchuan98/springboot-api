package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.utils.StringUtil;
import com.cc.entity.Coach;
import com.cc.service.CoachService;
import com.cc.vo.CoachVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-13
 */
@Controller
@RequestMapping("/api")
public class CoachController {
    @Autowired
    CoachService coachService;

    @RequestMapping("/coachdetail")
    @ResponseBody
    public JsonResult coachdetail(@RequestParam("coachId") String coachId) {
        if(StringUtil.isEmpty(coachId)){
            return  JsonResult.error("查询参数为空");
        }
        CoachVO coach=coachService.queryByCoachId(coachId);
        return  JsonResult.success("查询成功",coach);
    }
}
