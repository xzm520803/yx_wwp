package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


public class Teacher {

    @Excel(name = "教师ID",needMerge = true)
    private String id;

    @Excel(name = "教师姓名",needMerge = true)
    private String name;

    @ExcelCollection(name = "对应学生")
    private ArrayList<Emp> empList;

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", empList=" + empList +
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

    public ArrayList<Emp> getEmpList() {
        return empList;
    }

    public void setEmpList(ArrayList<Emp> empList) {
        this.empList = empList;
    }

    public Teacher() {
    }

    public Teacher(String id, String name, ArrayList<Emp> empList) {
        this.id = id;
        this.name = name;
        this.empList = empList;
    }
}
