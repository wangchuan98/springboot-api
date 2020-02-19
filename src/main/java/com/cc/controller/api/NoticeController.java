package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.utils.StringUtil;
import com.cc.entity.Notice;
import com.cc.service.NoticeService;
import com.cc.vo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-14
 */
@Controller
@RequestMapping("/api")
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @RequestMapping("/querynotice")
    @ResponseBody
    public JsonResult queryNotice(@RequestParam("page") Integer page){

        List<Notice> result= noticeService.queryNoticeList(page);
        return  JsonResult.success("查询成功",result);
    }


    @RequestMapping("/querynoticedetail")
    @ResponseBody
    public JsonResult queryNoticeDetail(@RequestParam("noticeId") String noticeId){

         Notice result= noticeService.queryNotice(noticeId);
        return  JsonResult.success("查询成功",result);
    }
}
