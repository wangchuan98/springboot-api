package com.cc.service;

import com.cc.vo.EvaluationVO;

import java.util.List;


public interface EvaluationService {
    //发布评论
    void publish(EvaluationVO vo);
    //查询某个教练的评价
    List<EvaluationVO> queryEvaluationByCoachId(String coachId,Integer page);
}
