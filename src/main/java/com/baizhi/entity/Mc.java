package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Mc {

    private String months;    //月份

    private Integer counts;      //数量

    @Override
    public String toString() {
        return "Mc{" +
                "months='" + months + '\'' +
                ", counts=" + counts +
                '}';
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Mc() {
    }

    public Mc(String months, Integer counts) {
        this.months = months;
        this.counts = counts;
    }
}
