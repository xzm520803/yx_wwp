package com.baizhi.entity;

import java.io.Serializable;
import java.util.List;

public class Sort implements Serializable {
    private String id;
    private String cate_name;  //类别名
    private Integer levels;     //级别
    private String parent_id;  //父类id

    private List<Sort> sortList;

    @Override
    public String toString() {
        return "Sort{" +
                "id='" + id + '\'' +
                ", cate_name='" + cate_name + '\'' +
                ", levels=" + levels +
                ", parent_id='" + parent_id + '\'' +
                ", sortList=" + sortList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<Sort> getSortList() {
        return sortList;
    }

    public void setSortList(List<Sort> sortList) {
        this.sortList = sortList;
    }

    public Sort() {
    }

    public Sort(String id, String cate_name, Integer levels, String parent_id, List<Sort> sortList) {
        this.id = id;
        this.cate_name = cate_name;
        this.levels = levels;
        this.parent_id = parent_id;
        this.sortList = sortList;
    }
}
