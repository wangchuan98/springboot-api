package com.cc.service.impl;

import com.cc.dao.NoticeMapper;
import com.cc.entity.Notice;
import com.cc.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-02-14
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> queryNoticeList(Integer page) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currIndex", (page<1?1:page - 1) * 5);
        param.put("pageSize", 5);
        List<Notice> result = noticeMapper.queryNotice(param);
        return result;
    }

    @Override
    public Notice queryNotice(String id) {
      return   noticeMapper.queryNoticeById(id);
    }
}
