package com.cc.vo;

import io.swagger.models.auth.In;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-15
 */
public class TableResultVO<E> {
    private Integer total;
    private List<E> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
