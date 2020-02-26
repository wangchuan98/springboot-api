package com.cc.entity;

/**
 * @program: springboot-api
 * @description:
 * @author: wangchuan
 * @create: 2020-01-19
 */
public class SubjectSkill {
    //技巧id
    private  String id;
    //科目
    private  Integer category;
    //技巧标题
    private  String title;
    //技巧内容
    private  String content;
    //创建人
    private  String creator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
