package com.cc.dao;

import com.cc.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    void insertUser(User user);
    String isExsitUserName(String username);
    User queryUserForLogin(@Param("username") String username, @Param("password") String password);
}
