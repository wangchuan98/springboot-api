package com.cc.entity;

/**
 * @program: springboot-api
 * @description: 用于记录分页信息
 * @author: wangchuan
 * @create: 2020-01-15
 */
public class PageInfo {
    //总数
    private  Integer total;
    //当前页数
    private  Integer pageNumber;
    //一页的大小
    private  Integer pageSize;

    public PageInfo(Integer pageNumber,Integer pageSize) {
        this.pageNumber =pageNumber==null?Integer.valueOf(1):pageNumber;
        this.pageSize = pageSize==null?Integer.valueOf(1):pageSize;
    }

    public Integer getCurrIndex(){
        return  (this.pageNumber-1)*this.pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
