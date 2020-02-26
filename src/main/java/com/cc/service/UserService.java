package com.cc.service;

import com.cc.entity.User;
import com.cc.vo.UserInfoVO;

public interface UserService {
    //插入用户
    public String insertUser(String userName, String passWord, Integer identity) throws Exception;
    //删除用户
    public void  deleteUser();
    //用户密码验证
    public boolean confirmPwd(String userId,String passWord);
    //更新用户
    public void  updateByUserId(String userId,String password,Integer identify)throws Exception;
    //用户登录
    public UserInfoVO queryUserForLogin(String username, String password,String face);
    //是否存在相同用户名
    public User isExsitUsername(String username);
    //用户注销
    public void logout(String identifyId);
}
