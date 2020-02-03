package com.cc.service.impl;

import com.cc.common.utils.DateUtil;
import com.cc.common.SnowflakeIdWorker;
import com.cc.common.utils.StringUtil;
import com.cc.common.enums.Status;
import com.cc.dao.TrainingOrderMapper;
import com.cc.entity.TrainingOrder;
import com.cc.service.TrainingOrderService;
import com.cc.vo.OrderStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2019-12-20
 */
@Service
public class TrainingOrderServiceImpl implements TrainingOrderService {
    @Autowired
    private TrainingOrderMapper orderMapper;
    private  final  int[] HAVEORDER_OR_ABSENCE={Status.hava_order.value(),Status.absence.value()};
    @Override
    public   List<List<OrderStatus>> queryForStutas(String coachid, Date begindate) {

        Date enddate=DateUtil.addDay(begindate,3);
        List<TrainingOrder> orderlist=orderMapper.queryForStutas(coachid,begindate,enddate);
        Map<Date,ArrayList<OrderStatus>> resultmap=new HashMap();
        Iterator<TrainingOrder> it=orderlist.iterator();
        //初始化map，放入四天的日期信息，以及对应的位置信息集合
        for(int i=0;i<4;i++)
        {
            ArrayList<OrderStatus> list=new ArrayList<OrderStatus>();
            resultmap.put(DateUtil.addDay(begindate,i),list);
        }
        TrainingOrder ordertmp=null;
        while (it.hasNext())
        {
            ordertmp=it.next();
            OrderStatus statustmp=new OrderStatus();
            BeanUtils.copyProperties(ordertmp,statustmp);
            if(resultmap.containsKey(ordertmp.getDate())){
                resultmap.get(ordertmp.getDate()).add(statustmp);
            }
        }
        List<List<OrderStatus>> resultlist=new ArrayList<>();
        //将查询到的结果集分好类之后再把map转成list,并且排好序
        for(int i=0;i<4;i++)
        {
            resultlist.add(resultmap.get(DateUtil.addDay(begindate,i)));
        }
        return resultlist;
    }

    @Override
    public boolean Order(TrainingOrder trainingOrder) {

        //查询这个时间端的数据是否存在
        TrainingOrder queryresult;
        queryresult = orderMapper.queryForExist(trainingOrder);
        if (queryresult == null) {
            String id=this.createId(trainingOrder);
            trainingOrder.setOrderid(String.valueOf(id));
            trainingOrder.setStatus(Status.hava_order.value());
            orderMapper.insertTrainingOrderOne(trainingOrder);
            return true;
        } else if (queryresult.getStatus() == Status.can_order.value()) {
            TrainingOrder updateorder = new TrainingOrder();
            updateorder.setOrderid(queryresult.getOrderid());
            updateorder.setStatus(Status.hava_order.value());
            updateorder.setCourseid(trainingOrder.getCourseid());
            orderMapper.updateTrainingOrderOne(updateorder);
            return true;
        } else {
            return false;
        }

    }

    private  String createId(TrainingOrder trainingOrder){
        String date= trainingOrder.getDate().toString();
        String time=trainingOrder.getBegintime().toString();
        String coachid=trainingOrder.getCoachid();
        return  date+time+coachid;
    }

    @Override
    public Integer queryHistoryCount(String studentid, Date date) {
        if(StringUtil.isEmpty(studentid)||date==null)
            return  Integer.valueOf(0);
        Map<String,Object> param=new HashMap<>();
        param.put("studentid",studentid);
        param.put("date",date);
        param.put("statusarray",HAVEORDER_OR_ABSENCE);
        Integer count=orderMapper.queryForHistoryCount(param);
        return count;

    }

    @Override
    public List<TrainingOrder> queryHistoryOrder(String studentid, Date date, Integer page, Integer pageSize) {
        Integer currIndex=page>0?(page-1)*pageSize:0;
        Map<String,Object> param=new HashMap<>();
        param.put("studentid",studentid);
        param.put("date",date);
        param.put("statusarray",HAVEORDER_OR_ABSENCE);
        param.put("currIndex",currIndex);
        param.put("pageSize",pageSize);
        List<TrainingOrder> result=orderMapper.queryHistoryOrder(param);
        return  result;
    }

    @Override
    public void cancelOrder(String orderid) {
        TrainingOrder updateorder = new TrainingOrder();
        updateorder.setOrderid(orderid);
        updateorder.setStatus(Status.can_order.value());
        orderMapper.updateTrainingOrderOne(updateorder);
    }

    @Override
    public TrainingOrder queryStudentToday(String courseid, Date date) {
        //查询这个学生当前有没有预约
        if(StringUtil.isEmpty(courseid)||date==null)
            return  null;
        TrainingOrder order=orderMapper.queryForTodayExist(courseid,date,Status.hava_order.value());
        return order;
    }

    @Override
    public TrainingOrder queryTodayDetail(String studentid, Date date) {
        if(StringUtil.isEmpty(studentid)||date==null)
            return  null;
        Map<String,Object> param=new HashMap<>();
        param.put("studentid",studentid);
        param.put("date",date);
        param.put("statusarray",HAVEORDER_OR_ABSENCE);
        TrainingOrder order=orderMapper.queryForTodaydetail(param);
        return order;
    }


}
