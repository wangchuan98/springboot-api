package com.cc;

import com.cc.common.JsonResult;
import com.cc.common.exception.AdminCommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: springboot-api
 * @description:管理员端异常处理类
 * @author: wangchuan
 * @create: 2020-02-24
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminCommonException.class)
    @ResponseBody
    public JsonResult adminException(Exception e) {
        return JsonResult.error(e.getMessage());
    }
}
