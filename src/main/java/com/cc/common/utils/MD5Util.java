package com.cc.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @program: springboot-api
 * @description: 一个用于密码加密的工具类
 * @author: wangchuan
 * @create: 2019-12-08
 */
public class MD5Util {
    /**
     * 根据传入的str，返回一个加密后的字符串
     * @param str
     * @return
     */
    public  static  String getMD5Str (String str)throws Exception
    {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(str.getBytes()));
        return newstr;
    }

    public static void main(String[] args) {
        String str="123";
        try {
            System.out.println(MD5Util.getMD5Str(str)); //4QrcOUm6Wau+VuBX8g+IPg==
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
