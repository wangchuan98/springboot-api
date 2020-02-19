package com.cc.dao;

import com.cc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    void insertUser(User user);
    Integer enablebyUserId(@Param("list") List<String> list, @Param("enable") Integer enable);
    User isExsitUserName(String username);
    void  updateByUserId(User user);
    User queryByUserId(@Param("userId") String userId);
    User queryUserForLogin(@Param("username") String username, @Param("password") String password);
}
