package com.cc.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-13
 */
@Component
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private  final  String  USER_SESSION="session_userId:";

    public  Long incr(String key, Date date ) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if (null == increment || increment.longValue() == 0) {//初始设置过期时间
            entityIdCounter.expireAt(date);
        }
        return increment;
    }

    public  String getSessionKey(String userId){
        String key=USER_SESSION+userId;
        Object value=redisTemplate.opsForValue().get(key);
        return  value==null?null:String.valueOf(value);
    }

    public  void setSessionKey(String userId,String session_key){
        String key=USER_SESSION+userId;
        redisTemplate.opsForValue().set(key,session_key);
    }
}
