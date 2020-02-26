package com.cc.dao;

import com.cc.entity.TrainingOrder;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface TrainingOrderMapper {

    //批量插入订单
    void insertTrainingOrderBatch(@Param("list") List<TrainingOrder> list);
    //批量修改订单,根据订单主键
    void updateTrainingOrderBatch(@Param("list") List<String> list,@Param("param") Map<String,Object> param );
    //插入订单
    void insertTrainingOrderOne(TrainingOrder order);
    //更新订单
    void updateTrainingOrderOne(TrainingOrder order);
    //查询某日所有的订单的订单状态
    List<TrainingOrder>  queryForStatus(@Param("coachid") String coachid,
                                        @Param("begindate")Date begindate,@Param("enddate")Date enddate);
    //历史订单总数
    Integer queryForHistoryCount(@Param("map") Map<String,Object> map);
    //历史订单列表
    List<TrainingOrder> queryHistoryOrder(@Param("map") Map<String,Object> map);
    //查询当天预约的详细信息
    TrainingOrder queryForTodaydetail(Map<String,Object> param);
    //查询某天某个时间段的数据是否存在
    TrainingOrder queryForExist(TrainingOrder order);
    //查询当天是否已经预约
    TrainingOrder  queryForTodayExist(@Param("courseid") String courseid,
                                 @Param("date")Date begindate,@Param("status")Integer status);
    //查询教练的某日订单
    List<TrainingOrder>  queryOnedayForCoach(@Param("coachId") String courseId,
                                      @Param("date")Date begindate,@Param("orderstatus") Integer orderstatus);


}
