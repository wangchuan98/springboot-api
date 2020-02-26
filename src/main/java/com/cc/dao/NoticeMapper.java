package com.cc.dao;

import com.cc.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    //查询返回公告列表
    List<Notice>  queryNotice(@Param("map") Map<String,Object> map);
    //返回单条公告信息
    Notice  queryNoticeById(@Param("id") String id);
}
