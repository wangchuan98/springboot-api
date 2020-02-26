package com.cc.service;

import com.cc.entity.PageInfo;
import com.cc.entity.Coach;
import com.cc.vo.CoachVO;
import com.cc.vo.CoachVO;

import java.util.List;

public interface CoachService {
    //更新教练信息
    String updatebyUserId(CoachVO coachVO, String userId);
    //查询教练
    CoachVO queryByCoachId(String coachId);
    //查询教练列表
    List<Coach> queryCoachList(String name, String coachId, String teachtype,PageInfo pageInfo);
    //计算教练总数
    Integer queryCountCoach(String name, String teachtype,String coachId);
    //插入教练
    String insertCoach(CoachVO coachVO,String userId);
    //更新教练信息
    void updatebyCoachId(CoachVO coachVO,boolean typechange);
    //教练失效
    void deletebyCoachId(List<String> idList);
}
