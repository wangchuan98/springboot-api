package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.RedisDao;
import com.cc.common.utils.MD5Util;
import com.cc.common.utils.StringUtil;
import com.cc.entity.User;
import com.cc.service.UserService;
import com.cc.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login")
    public JsonResult login(@RequestBody UserInfoVO userVO) throws Exception {
        String username = userVO.getUserName();
        String password = userVO.getPassWord();
        String face = userVO.getFace();
        System.out.println(face.length());
        //校验空值

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return JsonResult.error("用户名或密码为空");
        }
        String MDPassWord = MD5Util.getMD5Str(password);
        UserInfoVO result = userService.queryUserForLogin(username, MDPassWord, face);

        if (result != null) {
            return JsonResult.success("登陆成功", result);
        } else {
            return JsonResult.error("用户名或密码错误");
        }

    }

    @RequestMapping(value = "/confirmpwd")
    public JsonResult confirmPwd(@RequestBody UserInfoVO userVO) throws Exception {
        String userId = userVO.getUserId();
        String password = userVO.getPassWord();
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(password)) {
            return JsonResult.error("用户ID或密码为空");
        }
        String MDPassWord = MD5Util.getMD5Str(password);
        boolean flag = userService.confirmPwd(userId, MDPassWord);
        if (flag) {
            return JsonResult.success("验证成功", flag);
        } else {
            return JsonResult.error("密码错误");
        }

    }

    @RequestMapping(value = "/changepwd")
    public JsonResult changePwd(@RequestBody UserInfoVO userVO) throws Exception {
        String userId = userVO.getUserId();
        String password = userVO.getPassWord();
        Integer identify = userVO.getIdentify();
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(password)) {
            return JsonResult.error("用户ID或密码为空");
        }
        if (identify <= 0 || identify >= 3) {
            return JsonResult.error("修改失败");
        }
        userService.updateByUserId(userId, password, identify);
        return JsonResult.success("修改成功", null);


    }

    @RequestMapping(value = "/logout")
    public JsonResult logout(@RequestParam("identifyId") String identifyId) throws Exception {

        if (StringUtil.isEmpty(identifyId)) {
            return JsonResult.error("注销失败");
        }
        userService.logout(identifyId);
        return JsonResult.success("注销成功", null);

    }
}
