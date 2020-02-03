package com.cc.service;

import com.cc.entity.TrainingOrder;
import com.cc.vo.OrderStatus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface TrainingOrderService {
    List<List<OrderStatus>> queryForStutas(String coachid, Date date);
    boolean Order(TrainingOrder trainingOrder);
    Integer queryHistoryCount(String studentid,Date date);
    List<TrainingOrder> queryHistoryOrder(String studentid,Date date,Integer page,Integer pageSize);
    void cancelOrder(String orderid);
    TrainingOrder queryStudentToday(String courseid,Date date);
    TrainingOrder queryTodayDetail(String studentid,Date date);

}
