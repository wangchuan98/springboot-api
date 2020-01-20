package com.cc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-19
 */
@RequestMapping("/admin")
@Controller
public class AdminNoticeController {
@RequestMapping("/notice")
    public  String notice(){
    return "notice";
}
}
