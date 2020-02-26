package com.cc.controller.admin;

import com.cc.common.RedisDao;
import com.cc.common.utils.MD5Util;
import com.cc.common.utils.StringUtil;
import com.cc.service.UserService;
import com.cc.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: springboot-api
 * @description: 管理员登陆
 * @author: wangchuan
 * @create: 2020-01-10
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {


    @GetMapping({"/login"})
    public String login() {
        return "login";
    }
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "userName" ) String userName,
                        @RequestParam(value = "passWord")
                        String passWord, @RequestParam(value = "verifyCode") String verifyCode, HttpServletRequest request, HttpSession session) throws Exception {
        //用户名不清空
        request.setAttribute("userName",userName);
        //校验空值
        if(StringUtil.isEmpty(verifyCode))
        {
            request.setAttribute("errorMsg", "验证码不能为空");
            return "login";
        }
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(passWord)) {
            request.setAttribute("errorMsg", "用户名或密码不能为空");
            return "login";
        }
        //校验验证码
        String kaptchaCode = session.getAttribute("rightCode") + "";
        if (StringUtil.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            request.setAttribute("errorMsg", "验证码错误");
            return "login";
        }
        //校验密码
        String MDPassWord = MD5Util.getMD5Str(passWord);
        UserInfoVO result = userService.queryUserForLogin(userName, MDPassWord,null);
        if (result != null) {
            //将用户添加到sesion中
            session.setAttribute("userId", result.getAdminId());
            session.setAttribute("nickname",result.getNickname());
            session.setAttribute("session_key",result.getSessionkey());
            session.setMaxInactiveInterval(60 * 60 * 1);
            return "redirect:/admin/index";
        } else {
            request.setAttribute("errorMsg", "登陆失败");
            return "login";
        }

    }




}
