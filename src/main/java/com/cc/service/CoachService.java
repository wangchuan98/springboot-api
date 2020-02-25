package com.cc.service;

import com.cc.entity.PageInfo;
import com.cc.entity.Coach;
import com.cc.vo.CoachVO;
import com.cc.vo.CoachVO;

import java.util.List;

public interface CoachService {
    //根据userid更新coach表的值
    String updatebyUserId(CoachVO coachVO, String userId);
    CoachVO queryByCoachId(String coachId);
    List<Coach> queryCoachList(String name, String coachId, String teachtype,PageInfo pageInfo);
    Integer queryCountCoach(String name, String teachtype,String coachId);
    String insertCoach(CoachVO coachVO,String userId);
    void updatebyCoachId(CoachVO coachVO,boolean typechange);
    void deletebyCoachId(List<String> idList);
}
