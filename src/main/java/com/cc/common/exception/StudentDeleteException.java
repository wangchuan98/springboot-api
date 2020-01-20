package com.cc.common.exception;

import org.springframework.stereotype.Component;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-17
 */
public class StudentDeleteException extends RuntimeException {
    public StudentDeleteException(String msg) {
        super(msg);
    }

    public static void fail(String msg) {
        throw new StudentDeleteException(msg);
    }
}
