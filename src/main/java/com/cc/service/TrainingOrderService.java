package com.cc.service;

import com.cc.entity.TrainingOrder;
import com.cc.vo.OrderStatus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface TrainingOrderService {
    //返回一段日期内订单状态
    List<List<OrderStatus>> queryForStatus(Integer num,String coachid, Date date);
    //预约处理
    boolean Order(TrainingOrder trainingOrder);
    //教练调休
    void  insertHoliday(List<TrainingOrder> orderList);
    //取消调休
    void  cancelHoliday(List<String> ids);
    //返回历史单子总数
    Integer queryHistoryCount(String studentid,Date date);
    //返回历史订单列表
    List<TrainingOrder> queryHistoryOrder(String studentid,Date date,Integer page,Integer pageSize);
    //取消预约
    void cancelOrder(String orderid);
    //查询这个学员当天是否已经预约
    TrainingOrder queryStudentToday(String courseid,Date date);
    //查询学员的当天的预约详情
    TrainingOrder queryTodayDetail(String studentid,Date date);
   // 查询教练某日的订单
    List<TrainingOrder> queryOnedayForCoach(String coachId,Date date,Integer orderstatus);


}
