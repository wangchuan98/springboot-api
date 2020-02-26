package com.cc.vo;

import java.sql.Date;

/**
 * @program: springboot-api
 * @description:预约状态视图VO
 * @author: wangchuan
 * @create: 2019-12-20
 */
public class OrderStatus {
    //时间段位置
    private  String locationid;
    //时间段状态
    private  Integer status;



    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
