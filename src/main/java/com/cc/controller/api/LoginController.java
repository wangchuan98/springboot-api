package com.cc.controller.api;

import com.cc.common.JsonResult;
import com.cc.common.utils.MD5Util;
import com.cc.common.utils.StringUtil;
import com.cc.entity.User;
import com.cc.service.UserService;
import com.cc.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录接口")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="用户登录", notes="用户登录接口")
    @RequestMapping(value = "/login")
    private JsonResult login(@RequestBody User user) throws Exception  {
        String username = user.getUserName();
        String password = user.getPassWord();
        //校验空值
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return JsonResult.error("用户名或密码为空");
        }
            String MDPassWord = MD5Util.getMD5Str(password);
            UserInfoVO result = userService.queryUserForLogin(username, MDPassWord);
            if (result != null) {
                    return JsonResult.success("登陆成功", result);
            } else {
                return JsonResult.error("用户名或密码错误");
            }

    }
}
