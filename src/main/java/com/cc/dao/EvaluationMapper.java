package com.cc.dao;

import com.cc.entity.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EvaluationMapper {
    //根据教练id查询评论列表，按时间排序
    List<Evaluation> queryByCoachId(@Param("map")Map<String,Object> map);
    //插入评论
    void insert(Evaluation evaluation);
}
