package com.cc.dao;

import com.cc.entity.Admin;
import com.cc.entity.Student;

public interface AdminMapper {
    //查询单条Admin
    Admin querybyUserid(String userid);
}
