package com.cc.dao;

import com.cc.entity.SubjectSkill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SubjectSkillMapper {
    Integer[] querySkillCount();
    List<SubjectSkill> SkillqueryByParam(@Param("map")Map<String,Object> map);
    void deletebyId(String id);
}
