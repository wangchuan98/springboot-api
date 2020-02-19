package com.cc.service.impl;

import com.cc.common.utils.DateUtil;
import com.cc.common.utils.SnowflakeIdWorker;
import com.cc.common.utils.TimeUtil;
import com.cc.dao.EvaluationMapper;
import com.cc.entity.Evaluation;
import com.cc.service.EvaluationService;
import com.cc.vo.EvaluationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-14
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    EvaluationMapper evaluationMapper;
    @Override
    public void publish(EvaluationVO vo) {
        SnowflakeIdWorker snowflakeIdWorker=new SnowflakeIdWorker(4,1);
        Evaluation evaluation=new Evaluation();
        evaluation.setEvaluationId(String.valueOf(snowflakeIdWorker.nextId()));
        evaluation.setStudentId(vo.getStudentId());
        evaluation.setCoachId(vo.getCoachId());
        evaluation.setContent(vo.getContent());
        evaluation.setDate(vo.getDate());
        System.out.println(vo.getDate());
        evaluationMapper.insert(evaluation);

    }

    @Override
    public List<EvaluationVO> queryEvaluationByCoachId(String coachId,Integer page) {
        Map<String,Object> map=new HashMap<>();
        map.put("coachId",coachId);
        map.put("currIndex",(page-1)*5);
        map.put("pageSize",Integer.valueOf(5));
        List<Evaluation> list=evaluationMapper.queryByCoachId(map);
        List<EvaluationVO> result=new ArrayList<>();
        for (Evaluation evaluation:list){
            EvaluationVO vo=new EvaluationVO();
            vo.setStudentId(evaluation.getStudentId());
            vo.setContent(evaluation.getContent());
            vo.setFormateDate(TimeUtil.format(evaluation.getDate()));
            vo.setStudentName(evaluation.getStudent().getName());
            vo.setStudentFace(evaluation.getStudent().getFace());
            result.add(vo);
        }
        return   result;
    }
}
