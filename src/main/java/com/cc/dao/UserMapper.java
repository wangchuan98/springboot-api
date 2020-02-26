package com.cc.dao;

import com.cc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    //插入用户
    void insertUser(User user);
    //用户失效
    Integer enablebyUserId(@Param("list") List<String> list, @Param("enable") Integer enable);
    //用户名是否存在
    User isExsitUserName(String username);
    //更新用户
    void  updateByUserId(User user);
    //用户查询
    User queryByUserId(@Param("userId") String userId);
    //用户密码验证
    User queryUserForLogin(@Param("username") String username, @Param("password") String password);
}
