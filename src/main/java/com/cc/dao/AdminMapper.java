package com.cc.dao;

import com.cc.entity.Admin;
import com.cc.entity.Student;

public interface AdminMapper {
    Admin querybyUserid(String userid);
}
