package com.cc.service;

import com.cc.entity.User;
import com.cc.vo.UserInfoVO;

public interface UserService {
    public String insertUser(String userName, String passWord, Integer identity) throws Exception;
    public void  deleteUser();
    public void  updateByUserId(String userId,String password);
    public UserInfoVO queryUserForLogin(String username, String password);
    public User isExsitUsername(String username);
}
