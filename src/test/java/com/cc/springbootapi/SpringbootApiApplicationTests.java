package com.cc.springbootapi;

import com.cc.common.RedisDao;
import com.cc.common.utils.FlowCodeUtil;
import com.cc.dao.StudentMapper;
import com.cc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class SpringbootApiApplicationTests {

    @Autowired
    FlowCodeUtil flowCodeUtil;
    @Autowired
    StudentMapper mapper;
    @Test
    void contextLoads() {


        String str=flowCodeUtil.generateCode("S");


        System.out.println(str.substring(str.indexOf(":")+1));
    }

    @Test
    void test() {
        Map<String,Object> map=new HashMap<>();
        map.put("age",Integer.valueOf(20));
        //map.put("phone","10086");

        Set<String>  select=new HashSet<>();
        select.add("studentid");
        select.add("age");
//        List<Student> list=mapper.queryByMap(map);
//
//        for(Student item:list){
//            System.out.println(item);
//        }

    }

}
