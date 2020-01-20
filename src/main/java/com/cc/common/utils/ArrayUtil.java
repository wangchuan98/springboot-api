package com.cc.common.utils;

/**
 * @program: springboot-api
 * @description: 一个数组的工具类
 * @author: wangchuan
 * @create: 2019-12-25
 */
public class ArrayUtil {
    public static Boolean contain(String[] agrs,String str){
        Boolean flag=false;
        if(agrs==null||str==null)
            return  flag;
        for(String item:agrs) {
           if(item.equals(str))
               flag=true;
        }
        return  flag;
    }
}
