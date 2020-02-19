package com.cc.service.impl;

import com.cc.common.utils.FlowCodeUtil;
import com.cc.dao.CoachMapper;
import com.cc.dao.UserMapper;
import com.cc.entity.Coach;
import com.cc.entity.PageInfo;
import com.cc.entity.Coach;
import com.cc.entity.Coach;
import com.cc.service.CoachService;
import com.cc.vo.CoachVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-09
 */
@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    CoachMapper coachMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FlowCodeUtil flowCodeUtil;
    @Override
    public String updatebyUserId(CoachVO coachVO, String userId) {
        //先根据userId在查出coachId
        Coach coach=coachMapper.querybyUserid(userId);
        String coachId;
        if(coach!=null) {
            coachId=coach.getCoachId();
            HashMap<String, Object> map = new HashMap<>();
            map.put("coachId", coachId);
            map.put("sex", coachVO.getSex());
            map.put("name", coachVO.getName());
            map.put("phone", coachVO.getTel());
            map.put("age", coachVO.getAge());
            map.put("enable", Integer.valueOf(0));
            map.put("teachtype", coachVO.getTeachtype());
            map.put("coachcar", coachVO.getCoachcar());
            coachMapper.updatebyCoachId(map);
        }else{
            coachId=this.insertCoach(coachVO,userId);
        }
        return  coachId;
    }

    @Override
    public CoachVO queryByCoachId(String coachId) {
        List<String> ids=new ArrayList<>();
        ids.add(coachId);
        List<Coach> list = coachMapper.queryByCoachId(ids);
        if(list.size()>0)
        {
            Coach coach= list.get(0);
            CoachVO vo=new CoachVO();
            vo.setCoachId(coach.getCoachId());
            vo.setName(coach.getName());
            vo.setSex(coach.getSex());
            vo.setAge(coach.getAge());
            vo.setTel( coach.getPhone());
            vo.setCoachcar(coach.getCoachcar());
            vo.setTeachtype(coach.getTeachtype());
            vo.setWorkphoto(Coach.PHOTO_PREFIX+coachId+Coach.PHOTO_SUFFIX);
            return vo;

        }

        else
            return null;
    }

    @Override
    public List<Coach> queryByParam(String name, String coachId, PageInfo pageInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("coachId", coachId);
        map.put("name", name);
        map.put("currIndex", pageInfo.getCurrIndex());
        map.put("pageSize", pageInfo.getPageSize());
        List<Coach> list = coachMapper.queryByParams(map);
        return list;
    }

    @Override
    public Integer queryCountByParam(String name, String coachId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("coachId", coachId);
        map.put("name", name);
        Integer count = coachMapper.queryCountByParams(map);
        return count;
    }

    @Override
    public String insertCoach(CoachVO coachVO, String userId) {
        //创建coach
        Coach coach = new Coach();
        String coachId = flowCodeUtil.generateCode("C");
        coach.setCoachId(coachId);
        coach.setAge(coachVO.getAge());
        coach.setName(coachVO.getName());
        coach.setPhone(coachVO.getTel());
        coach.setSex(coachVO.getSex());
        coach.setCoachcar(coachVO.getCoachcar());
        coach.setTeachtype(coachVO.getTeachtype());
        coach.setUserid(userId);
        coachMapper.coachInsert(coach);
        return coach.getCoachId();
    }

    @Override
    public boolean updatebyCoachId(CoachVO coachVO) {
        return false;
    }

    @Override
    public void deletebyCoachId(List<String> idList) {

        //更具coachId 查出userid集合，把userid的启用状态也置为1
        List<Coach> resultList=coachMapper.queryByCoachId(idList);
        List<String> userIdList=new ArrayList<>();
        for(Coach item:resultList){
            userIdList.add(item.getUserid());
        }
        coachMapper.enablebyCoachId(idList,Integer.valueOf(1));
        userMapper.enablebyUserId(userIdList,Integer.valueOf(1));
    }
}
