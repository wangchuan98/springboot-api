package com.cc.common;

/**
 *
 * 一个用于向前端返回json数据的类.
 * 有几种情况
 * 一.成功返回  status为200
 * 二.表示错误  status为500
 * 三.抛出异常  status为555
 *
 */
public class JsonResult {
    //返回的消息
     private  String msg;
    //返回的数据
     private  Object data;
     //返回的状态
     private  Integer status;




    public JsonResult(String msg, Object data, Integer status) {
        this.msg = msg;
        this.data = data;
        this.status = status;
    }


    /**
     * 成功返回时调用
     * @param msg
     * @param data
     * @return
     */
    public   static JsonResult  success(String msg,Object data){ return  new JsonResult(msg,data,200); }


    public   static JsonResult  success(String msg){ return  new JsonResult(msg,null,200); }

    /**
     * 成功返回时调用，不需要传参
     * @return
     */
    public   static JsonResult  success(){ return  new JsonResult("",null,200); }

    /**
     * 有错误信息时调用+++++++++++++++++++
     * @param msg
     * @return
     */
    public   static JsonResult  error(String msg){
        return  new JsonResult(msg,null,500);
    }


    /**
     * 抛出异常时调用
     * @param msg
     * @return
     */
    public   static JsonResult  exception(String msg){
        return  new JsonResult(msg,null,555);
    }

    /*
     未登陆或者账号被挤出时使用
     */
    public static JsonResult errorSessionMsg(String msg) {
        return new JsonResult(msg, null, 502);
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
