package com.cc.dao;

import com.cc.entity.TrainingOrder;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface TrainingOrderMapper {
    List<TrainingOrder>  queryForStutas(@Param("coachid") String coachid,
                                        @Param("begindate")Date begindate,@Param("enddate")Date enddate);
    void insertTrainingOrderOne(TrainingOrder order);
    void updateTrainingOrderOne(TrainingOrder order);

    Integer queryForHistoryCount(@Param("map") Map<String,Object> map);
    List<TrainingOrder> queryHistoryOrder(@Param("map") Map<String,Object> map);
    //查询当天预约的详细信息
    TrainingOrder queryForTodaydetail(Map<String,Object> param);
    //查询某天某个时间段的数据是否存在
    TrainingOrder queryForExist(TrainingOrder order);
    //查询当天是否已经预约
    TrainingOrder  queryForTodayExist(@Param("courseid") String courseid,
                                 @Param("date")Date begindate,@Param("status")Integer status);
}
