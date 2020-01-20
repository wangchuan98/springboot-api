package com.cc.common.utils;

import com.cc.common.RedisDao;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: springboot-api
 * @description: 生成流水号
 * @author: wangchuan
 * @create: 2020-01-13
 */
@Component
public class FlowCodeUtil {
    private  final static String PREX="flow_number";
    @Autowired
    private RedisDao redisDao;






    private static String  getRedisKey(String bizType) {
        //获得当前日期
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return  PREX+ ":" + bizType + "-" + date;
    }

    /**
     * 获得自动补0的相应位数的值
     *
     * @param seq    数值
     * @param length 长度
     *
     * @return 一定位数的序列号
     */
    private static String getSequence(long seq, int length) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len > length) {
            return str;
        }
        int rest = length - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }




    /**
     * 获取某一天的0时0分0秒0毫秒
     *
     * @param amount 日增减量
     *
     * @return 时间
     */
    private Date getDay(int amount) {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        // 加一天
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        // 清空时/分/秒/毫秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public String generateCode(String bizCode, int inrNumberLength) {
        String key = getRedisKey(bizCode);
        long number = redisDao.incr(key,this.getDay(1));
        if (String.valueOf(number).length() > inrNumberLength) {
            //抛出自定义异常
            //throw new Exception("编码超出范围！");
        }
        return key + getSequence(number, inrNumberLength);
    }

    public String generateCode(String bizCode) {
        String str=this.generateCode(bizCode, 5);
        return str.substring(str.indexOf(":")+1);
    }



}
