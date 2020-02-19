package com.cc.service;

import com.cc.entity.Notice;

import java.util.List;

public interface NoticeService {

    //分页查询公告
    List<Notice>  queryNoticeList(Integer page);
    //查询公告
    Notice  queryNotice(String id);
}
