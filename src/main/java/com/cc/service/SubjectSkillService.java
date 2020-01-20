package com.cc.service;


import com.cc.entity.PageInfo;
import com.cc.entity.SubjectSkill;

import java.util.List;
import java.util.Map;

public interface SubjectSkillService {

    Map<String,Integer> querySkillCount();
    List<SubjectSkill> queryByParam(Integer category,PageInfo pageInfo);
    void deletebyId(String id);

}
