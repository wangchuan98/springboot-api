package com.cc.service.impl;

import com.cc.common.JsonResult;
import com.cc.common.exception.AdminCommonException;
import com.cc.common.utils.FlowCodeUtil;
import com.cc.common.utils.SnowflakeIdWorker;
import com.cc.dao.CoachMapper;
import com.cc.dao.CourseMapper;
import com.cc.dao.UserMapper;
import com.cc.entity.*;
import com.cc.entity.Coach;
import com.cc.entity.Coach;
import com.cc.service.CoachService;
import com.cc.vo.CoachVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    private CoachMapper coachMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private FlowCodeUtil flowCodeUtil;

    private final  String  SUPERSTUDENTID="default";
    @Override
    public String updatebyUserId(CoachVO coachVO, String userId) {
        //先根据userId在查出coachId
        Coach coach = coachMapper.querybyUserid(userId);
        String coachId;
        if (coach != null) {
            coachId = coach.getCoachId();
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
        } else {
            coachId = this.insertCoach(coachVO, userId);
        }
        return coachId;
    }

    @Override
    public CoachVO queryByCoachId(String coachId) {
        List<String> ids = new ArrayList<>();
        ids.add(coachId);
        List<Coach> list = coachMapper.queryByCoachId(ids);
        if (list.size() > 0) {
            Coach coach = list.get(0);
            CoachVO vo = new CoachVO();
            vo.setCoachId(coach.getCoachId());
            vo.setName(coach.getName());
            vo.setSex(coach.getSex());
            vo.setAge(coach.getAge());
            vo.setTel(coach.getPhone());
            vo.setCoachcar(coach.getCoachcar());
            vo.setTeachtype(coach.getTeachtype());
            vo.setWorkphoto(Coach.PHOTO_PREFIX + coachId + Coach.PHOTO_SUFFIX);
            return vo;

        } else
            return null;
    }

    @Override
    public List<Coach> queryCoachList(String name, String coachId,String teachtype, PageInfo pageInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("coachId", coachId);
        map.put("name", name);
        map.put("teachtype", teachtype);
        map.put("currIndex", pageInfo.getCurrIndex());
        map.put("pageSize", pageInfo.getPageSize());
        List<Coach> list = coachMapper.queryByParams(map);
        return list;
    }

    @Override
    public Integer queryCountCoach(String name, String teachtype,String coachId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("coachId", coachId);
        map.put("name", name);
        map.put("teachtype", teachtype);
        Integer count = coachMapper.queryCountByParams(map);
        return count;
    }

    @Override
    public String insertCoach(CoachVO coachVO, String userId) {
        Date creattime = new Date(System.currentTimeMillis());
        //创建coach
        Coach coach = new Coach();
        String coachId = flowCodeUtil.generateCode("C");
        coach.setCoachId(coachId);
        coach.setAge(coachVO.getAge());
        coach.setName(coachVO.getName());
        coach.setPhone(coachVO.getTel());
        coach.setSex(coachVO.getSex());
        coach.setCreattime(creattime);
        coach.setCreator(coachVO.getCreator());
        coach.setCoachcar(coachVO.getCoachcar());
        coach.setTeachtype(coachVO.getTeachtype());
        coach.setUserid(userId);
        coachMapper.coachInsert(coach);
        //新增完成后，为教练指派默认课程，用于休假
        Course course = new Course();
        //生成courseId
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(5, 1);
        long id = snowflakeIdWorker.nextId();
        course.setCourseid(String.valueOf(id));
        course.setCoachid(coachId);
        course.setStudentid(SUPERSTUDENTID);
        course.setStatus(Integer.valueOf(2));
        course.setCoursetype(coach.getTeachtype());
        courseMapper.insertCourse(course);
        return coach.getCoachId();
    }

    @Override
    public void updatebyCoachId(CoachVO coachVO,boolean typechange) {
        //如果教学类型改变了，先查下教练有没有正在进行的课程，有的话不让修改
        if(typechange){
            ArrayList<String> ids=new ArrayList<>();
            ids.add(coachVO.getCoachId());
            this.courseVarify(ids);
        }
        HashMap<String,Object>  map=new HashMap<>();
        map.put("coachId",coachVO.getCoachId());
        map.put("name",coachVO.getName());
        map.put("age",coachVO.getAge());
        map.put("sex",coachVO.getSex());
        map.put("phone",coachVO.getTel());
        map.put("coachcar",coachVO.getCoachcar());
        map.put("teachtype",coachVO.getTeachtype());
        coachMapper.updatebyCoachId(map);

    }

    @Override
    public void deletebyCoachId(List<String> idList) {
        //先查询是否有进行的课程
        this.courseVarify(idList);
        //更具coachId 查出userid集合，把userid的启用状态也置为1
        List<Coach> resultList = coachMapper.queryByCoachId(idList);
        List<String> userIdList = new ArrayList<>();
        for (Coach item : resultList) {
            userIdList.add(item.getUserid());
        }
        coachMapper.enablebyCoachId(idList, Integer.valueOf(1));
        userMapper.enablebyUserId(userIdList, Integer.valueOf(1));
    }


    private void courseVarify(List<String> idList){
        HashMap<String,Object> param=new HashMap<>();
        List<Course> courseList=courseMapper.queryByCoachId(idList,param);
        if(courseList.size()>0){
            String msg="教练：";
            for(Course item:courseList){
                msg=msg+item.getCoachid()+" ";
            }
            msg=msg+"仍存在进行的课程";
            throw  new AdminCommonException(msg);
        }
    }
}
