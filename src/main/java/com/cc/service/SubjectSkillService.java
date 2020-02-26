package com.cc.service;


import com.cc.entity.PageInfo;
import com.cc.entity.SubjectSkill;

import java.util.List;
import java.util.Map;

public interface SubjectSkillService {

    //查询技巧总数
    Map<String,Integer> querySkillCount();
    //按科目查询技巧
    List<SubjectSkill> queryByParam(Integer category,PageInfo pageInfo);
    //删除技巧
    void deletebyId(String id);

}
