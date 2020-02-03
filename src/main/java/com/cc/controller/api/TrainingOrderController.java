package com.cc.controller.api;

import com.cc.common.utils.ArrayUtil;
import com.cc.common.JsonResult;
import com.cc.common.utils.StringUtil;
import com.cc.entity.TrainingOrder;
import com.cc.service.TrainingOrderService;
import com.cc.vo.OrderStatus;
import com.cc.vo.PageListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2019-12-20
 */
@RestController
@Api(tags = "预约接口")
@RequestMapping("/api")
public class TrainingOrderController {
    //2019-12-20
    @Autowired
    private TrainingOrderService orderService;
    private  final  Integer PAGESIZE=5;
    private  final  String[] ORDER_CANNULL={"orderid","status","studentid","coachid","coach","student","course"};

    @ApiOperation(value="预约状态查询", notes="预约状态查询接口")
    @RequestMapping(value = "/queryforstatus")
    private JsonResult queryforstatus(@RequestParam("coachid")String coachid, @RequestParam("date")Date date) throws Exception  {
        //校验空值
        if (StringUtil.isEmpty(coachid) || date==null) {
            return JsonResult.error("查询参数错误");
        }
        List<List<OrderStatus>> result = orderService.queryForStutas(coachid,date);

            return JsonResult.success("查询成功", result);


    }


    @ApiOperation(value="预约", notes="预约接口")
    @RequestMapping(value = "/order")
    private JsonResult order(@RequestBody  TrainingOrder trainingOrder) throws Exception  {
        //校验空值
        if (ordetIsEmpty(trainingOrder)) {
            return JsonResult.error("信息存在空值");
        }
        TrainingOrder todayorder=orderService.queryStudentToday(trainingOrder.getCourseid(),trainingOrder.getDate());
        if(todayorder!=null)
            return  JsonResult.success("今日已预约",null);
        boolean flag=orderService.Order(trainingOrder);
        if(flag){
            return JsonResult.success("预约成功", null);
        }else {
            return JsonResult.success("预约失败", null);
        }
    }


    @ApiOperation(value="取消预约", notes="取消预约接口")
    @RequestMapping(value = "/cancelorder")
    private JsonResult cancelorder(@RequestParam("orderid")String orderid) throws Exception  {
        //校验空值
        if ( StringUtil.isEmpty(orderid)) {
            return JsonResult.error("信息存在空值");
        }
       orderService.cancelOrder(orderid);
        return JsonResult.success("取消预约成功", null);

    }

    @ApiOperation(value="今日预约信息", notes="今日预约信息接口")
    @RequestMapping(value = "/ordertodaydetail")
    private JsonResult ordertodaydetail(@RequestParam("studentid")String studentid, @RequestParam("date")Date date) throws Exception {
        //校验空值
        if ( StringUtil.isEmpty(studentid) || date == null) {
            return JsonResult.error("信息存在空值");
        }
        TrainingOrder todayorder = orderService.queryTodayDetail(studentid, date);
        if (todayorder == null)
            return JsonResult.success("今日还没有预约", null);
         else {
            return JsonResult.success("查询今日预约信息成功", todayorder);
        }
    }


    @ApiOperation(value="历史预约信息", notes="历史预约信息接口")
    @RequestMapping(value = "/orderhistory")
    private JsonResult orderhistory(@RequestParam("studentid")String studentid, @RequestParam("date")Date date,
    @RequestParam("page")Integer page) throws Exception {
        PageListVO<TrainingOrder> pageListVO=new PageListVO<>();
        //校验空值
        if ( StringUtil.isEmpty(studentid) || date == null) {
            return JsonResult.error("信息存在空值");
        }
        if(page==null){
            page=Integer.valueOf(1);
        }
        //加载第一页数据时，查询下历史订单的总数
        if(page==1){
           Integer total=orderService.queryHistoryCount(studentid,date);
           if(total==0){
               return JsonResult.success("暂无历史订单", null);
           }
            pageListVO.setTotalPage(total/5);
        }
        //查询page页的数据返回
        List<TrainingOrder> resultlist=orderService.queryHistoryOrder(studentid,date,page,PAGESIZE);
        pageListVO.setRows(resultlist);
        return JsonResult.success("查询成功",pageListVO);
    }
    private  boolean ordetIsEmpty( TrainingOrder trainingOrder){
        //查看除了status 和id之外的属性是否为空
        boolean flag=false;
        if(trainingOrder==null)
            flag=true;
        Field[] fields = trainingOrder.getClass().getDeclaredFields();
        try {
            for(Field field:fields) {
                field.setAccessible(true);
                Object obj=field.get(trainingOrder);
                //除了数组中的字段属性，其他不能为空
                if (obj==null&&!ArrayUtil.contain(ORDER_CANNULL,field.getName())) {
                flag=true;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            flag=true;
        }finally {
            return  flag;
        }




    }
}
