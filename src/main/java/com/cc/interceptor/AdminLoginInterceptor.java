package com.cc.interceptor;

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




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI();
        System.out.println("进入拦截器："+requestUrl);
        /*
        if(null == request.getSession().getAttribute("userId")){
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }
        // 放行
        */
        return true;
    }


}
