package com.cc.dao;

import com.cc.entity.Coach;
import com.cc.entity. Coach;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CoachMapper {
    //根据userid查出coach
    Coach querybyUserid(String userid);
    //用coachId批量查询Coach
    List< Coach> queryByCoachId(@Param("list") List<String> list);
    //根据查询条件分页查询
    List< Coach> queryByParams(@Param("map") Map<String,Object> map);
    //根据查询条件查询总数量
    Integer queryCountByParams(@Param("map") Map<String,Object> map);
    //插入一个coach
    void coachInsert(Coach coach);

    Integer updatebyCoachId(@Param("map") Map<String,Object> map);

    Integer enablebyCoachId(@Param("list") List<String> list,@Param("enable") Integer enable);
}
