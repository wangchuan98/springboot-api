package com.cc.service.impl;

import com.cc.dao.SubjectSkillMapper;
import com.cc.entity.PageInfo;
import com.cc.entity.SubjectSkill;
import com.cc.service.SubjectSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-17
 */
@Service
public class SubjectSkillServiceImpl implements SubjectSkillService {

    @Autowired
    SubjectSkillMapper subjectSkillMapper;
    @Override
    public Map<String, Integer> querySkillCount() {

        Integer[] arr=subjectSkillMapper.querySkillCount();
        HashMap<String,Integer> map=new HashMap<>();
        map.put("subject2",arr[0]==null?0:arr[0]);
        map.put("subject3",arr[1]==null?0:arr[1]);
        return map;

    }

    @Override
    public List<SubjectSkill> queryByParam(Integer category, PageInfo pageInfo) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("category", category);
        map.put("currIndex", pageInfo.getCurrIndex());
        map.put("pageSize", pageInfo.getPageSize());
        List<SubjectSkill> list=subjectSkillMapper.SkillqueryByParam(map);
        return list;
    }



    @Override
    public void deletebyId(String id) {
            subjectSkillMapper.deletebyId(id);
    }
}
