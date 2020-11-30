package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;


public class Emp {

    @Excel(name = "ID")
    private String id;

    @Excel(name = "头像",type = 2)
    private String name;
    @Excel(name="年龄")
    private Integer age;
    @Excel(name = "生日",exportFormat="yyyy-MM-dd",importFormat="yyyy-MM-dd")
    private Date birthday;

    public Emp(String id, String name, Integer age, Date birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Emp() {
    }
}
