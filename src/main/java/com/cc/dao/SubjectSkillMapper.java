package com.cc.dao;

import com.cc.entity.SubjectSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubjectSkillMapper {
    //查询技巧总数
    Integer[] querySkillCount();
    //查询技巧列表
    List<SubjectSkill> SkillqueryByParam(@Param("map")Map<String,Object> map);
    //删除
    void deletebyId(String id);
}
