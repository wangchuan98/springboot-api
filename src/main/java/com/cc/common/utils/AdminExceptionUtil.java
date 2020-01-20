package com.cc.common.utils;

import com.cc.common.JsonResult;
import com.cc.common.exception.StudentDeleteException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-17
 */
@ControllerAdvice
public class AdminExceptionUtil {

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public JsonResult handleException(Exception ex) {
//        return  JsonResult.error(ex.getMessage());
//    }
    @ExceptionHandler(StudentDeleteException.class)
    @ResponseBody
    public JsonResult SQLConstrainthandleException(Exception ex) {
            return  JsonResult.error(ex.getMessage());
    }

}
