package com.cc.service;

import com.cc.entity.User;
import com.cc.vo.UserInfoVO;

public interface UserService {
    public String insertUser(String userName, String passWord, Integer identity) throws Exception;
    public void  deleteUser();
    public boolean confirmPwd(String userId,String passWord);
    public void  updateByUserId(String userId,String password,Integer identify)throws Exception;
    public UserInfoVO queryUserForLogin(String username, String password,String face);
    public User isExsitUsername(String username);
    public void logout(String identifyId);
}
