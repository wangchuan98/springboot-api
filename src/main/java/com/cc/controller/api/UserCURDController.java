package com.cc.controller.api;

import com.cc.entity.User;
import com.cc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户增改接口")
public class UserCURDController {
    @Autowired
    private UserService userService;
    @ApiOperation(value="插入用户", notes="插入用户")
    @RequestMapping(value = "/insertuser")
    public  String insertUser()
    {
             return null;
    }
}
