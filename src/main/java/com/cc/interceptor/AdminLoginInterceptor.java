package com.cc.interceptor;

import com.cc.common.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-10
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {



    @Autowired
    RedisDao redisDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();
        System.out.println("进入拦截器："+requestUrl);
        if(1==1){
            return  true;
        }
        Object userId=request.getSession().getAttribute("userId");
        Object session_keyObj=request.getSession().getAttribute("session_key");
        if(null == userId||null ==session_keyObj){
            response.sendRedirect(request.getContextPath()+"/admin/relogin");
            return false;
        }else {
            String session_key=session_keyObj.toString();
            String redis_session_key=redisDao.getSessionKey(userId.toString());
            if(!session_key.equals(redis_session_key)){
                response.sendRedirect(request.getContextPath()+"/admin/relogin");
                return false;
            }

        }

        //

        // 放行
        return true;
    }


}
