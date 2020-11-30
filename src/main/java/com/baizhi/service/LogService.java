package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.List;

public interface LogService {
    //查所有    起始条数      结束条数
    List<Log> findLog(Integer page, Integer rows);

    //查总条数
    Integer totalCounts();

}
