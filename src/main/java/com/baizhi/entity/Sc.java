package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class Sc {

    private String sex;
    private List<City> citys;

    @Override
    public String toString() {
        return "Sc{" +
                "sex='" + sex + '\'' +
                ", citys=" + citys +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    public Sc() {
    }

    public Sc(String sex, List<City> citys) {
        this.sex = sex;
        this.citys = citys;
    }
}
