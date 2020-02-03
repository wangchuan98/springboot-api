package com.cc.service.impl;

import com.cc.common.RedisDao;
import com.cc.common.SnowflakeIdWorker;
import com.cc.common.utils.FlowCodeUtil;
import com.cc.common.utils.MD5Util;
import com.cc.common.utils.StringUtil;
import com.cc.dao.AdminMapper;
import com.cc.dao.StudentMapper;
import com.cc.dao.UserMapper;
import com.cc.entity.Admin;
import com.cc.entity.Student;
import com.cc.entity.User;
import com.cc.service.StudentService;
import com.cc.service.UserService;
import com.cc.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    private RedisDao redisDao;

    @Override
    public String insertUser(String userName, String passWord, Integer identity) throws Exception {

        //创建user
        User user = new User();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2, 1);

        //加密密码
        String MD5PassWord = MD5Util.getMD5Str(passWord);
        Long userid = idWorker.nextId();

        user.setUserName(userName);
        user.setPassWord(MD5PassWord);
        user.setUserId(String.valueOf(userid));
        user.setIdentity(identity);

        userMapper.insertUser(user);
        return user.getUserId();
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void updateByUserId(String userId, String password) {
        User newUser=new User();
        newUser.setIdentity(User.STUDENT);
        newUser.setUserId(userId);
        newUser.setPassWord(password);
        newUser.setEnable(Integer.valueOf(0));
        userMapper.updateByUserId(newUser);
    }

    @Override
    public UserInfoVO queryUserForLogin(String username, String password) {

        User result = userMapper.queryUserForLogin(username, password);
        UserInfoVO vo = null;
        if (result != null) {
            vo = new UserInfoVO();
            //生成一个session_key
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(3, 0);
            long sessionkey = idWorker.nextId();
            vo.setSessionkey(String.valueOf(sessionkey));

            String userid = result.getUserId();
            //判断身份，如果是 学员就返回学员的信息
            if (result.getIdentity() == User.STUDENT) {
                Student student = studentMapper.querybyUserid(userid);
                vo.setStudentId(student.getStudentId());
                //在redis中添加session_key
                redisDao.setSessionKey(student.getStudentId(), vo.getSessionkey());

            } else if (result.getIdentity() == User.ADMIN) {
                //根据userid查管理员表
                Admin admin = adminMapper.querybyUserid(userid);
                vo.setAdminId(admin.getAdminid());
                vo.setNickname(admin.getNickname());
                //在redis中添加session_key
                redisDao.setSessionKey(admin.getAdminid(), vo.getSessionkey());
            }
            vo.setIdentify(result.getIdentity());
        }
        return vo;
    }

    @Override
    public User isExsitUsername(String username) {
        User user = userMapper.isExsitUserName(username);
        return  user;

    }
}
