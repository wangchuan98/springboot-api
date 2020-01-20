package com.cc.common.enums;

//订单状态的枚举类
//状态 状态:1未预约2已预约3调休4.缺勤
public enum Status {
    can_order(1),hava_order(2),rest(3),absence(4);
    private final int value;
    private Status(int value)
    {
        this.value=value;
    }
    public int value() {
        return value;
    }


}
