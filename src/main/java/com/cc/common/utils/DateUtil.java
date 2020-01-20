package com.cc.common.utils;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @program: springboot-api
 * @description: 一个日期的工具类
 * @author: wangchuan
 * @create: 2019-12-20
 */
public class DateUtil {

    /**
     *
     * @param date 要做增减的日期
     * @param n  要增加的天数
     * @return
     */
    public static Date addDay(Date date,int n){

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,n); //把日期往后增加一天,整数  往后推,负数往前移动
         java.util.Date newdate=calendar.getTime();
         java.sql.Date result = new java.sql.Date(newdate.getTime());
        return result;
    }
}
