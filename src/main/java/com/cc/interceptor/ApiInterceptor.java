package com.cc.interceptor;

import com.alibaba.fastjson.JSON;
import com.cc.common.JsonResult;
import com.cc.common.RedisDao;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: springboot-api
 * @description:小程序端登录拦截器
 * @author: wangchuan
 * @create: 2020-01-31
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    @Autowired
    RedisDao redisDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();
        System.out.println("进入小程序端拦截器："+requestUrl);
        String id =request.getHeader("headerUserId");
        String sessionKey=request.getHeader("headerSessionKey");
        if(null == id||null ==sessionKey){
            JsonResult result=JsonResult.errorSessionMsg("请登陆");
            returnResponse(response,result);
            return false;
        }else {
            String redis_session_key=redisDao.getSessionKey(id.toString());
            if(!sessionKey.equals(redis_session_key)){
                JsonResult result=JsonResult.errorSessionMsg("用户在其他地点登陆!");
                returnResponse(response,result);
                return false;
            }

        }

        //

        // 放行
        return true;
    }



    public void returnResponse(HttpServletResponse response, JsonResult result)
            throws IOException
    {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JSON.toJSONString(result).getBytes("utf-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }
}
