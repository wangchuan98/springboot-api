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
    void cancelOrder(String orderid);
    TrainingOrder queryStudentToday(String studentid,Date date);
    TrainingOrder queryTodayDetail(String studentid,Date date);
}
