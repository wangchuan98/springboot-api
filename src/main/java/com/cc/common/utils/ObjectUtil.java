package com.cc.common.utils;

import com.cc.entity.TrainingOrder;

import java.lang.reflect.Field;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-13
 */
public class ObjectUtil {

    public static boolean  objectIsEmpty( Object target,String[] ExpArr ) {
        //查看除了status 和id之外的属性是否为空
        boolean flag = false;
        if (target == null)
            flag = true;
        Field[] fields = target.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object obj = field.get(target);
                //除了数组中的字段属性，其他不能为空
                if (obj == null && !ArrayUtil.contain(ExpArr, field.getName())) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = true;
        } finally {
            return flag;
        }
    }
}
