package com.cc.entity;

import com.sun.javafx.beans.IDProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户对象", description="用户对象")
public class User {
    public   static  final  Integer  STUDENT=1;
    public   static  final  Integer  CAOCH=2;
    public   static  final  Integer  ADMIN=3;
    //主键id
    @ApiModelProperty( value="id", name="id" ,hidden = true)
    private  String userId;
    //用户名
    @ApiModelProperty(value="用户名", name="username", example="10086", required=true)
    private  String userName;
    //密码
    @ApiModelProperty(value="密码", name="password", example="123", required=true)
    private  String passWord;
    //身份 1是学员 2是教练
    @ApiModelProperty(value="身份", name="identity", example="1", required=true)
    private  Integer identity;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }


}
