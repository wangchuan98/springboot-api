package com.cc.common.exception;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-24
 */
public class CommonException extends RuntimeException {
    public CommonException(String msg){
        super(msg);
    }

    public static void fail(String msg) {
        return ;
    }
}
