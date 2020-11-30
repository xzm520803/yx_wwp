package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "名字")
    private String name;     //用户
    @Excel(name = "电话")
    private String iphone;   //电话
    @Excel(name = "头像", type = 2)
    private String head;     //头像
    @Excel(name = "简介")
    private String brief;    //简介
    @Excel(name = "学分")
    private Double score;    //学分
    @Excel(name = "创建时间")
    private Date createDate; //创建时间
    @Excel(name = "状态")
    private String state;     //状态
    @Excel(name = "性别")
    private String sex;     //性别
    @Excel(name = "城市")
    private String city;     //城市

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iphone='" + iphone + '\'' +
                ", head='" + head + '\'' +
                ", brief='" + brief + '\'' +
                ", score=" + score +
                ", createDate=" + createDate +
                ", state='" + state + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User() {
    }

    public User(String id, String name, String iphone, String head, String brief, Double score, Date createDate, String state, String sex, String city) {
        this.id = id;
        this.name = name;
        this.iphone = iphone;
        this.head = head;
        this.brief = brief;
        this.score = score;
        this.createDate = createDate;
        this.state = state;
        this.sex = sex;
        this.city = city;
    }
}
