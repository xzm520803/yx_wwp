package com.baizhi.service;

import com.baizhi.entity.City;
import com.baizhi.entity.Mc;
import com.baizhi.entity.User;

import java.util.List;


public interface UserService {
    //查所有    起始条数      结束条数
    List<User> findAll(int page, int rows);
    //查总条数
    Integer totalCounts();

    //修改状态
    void update(User user);
    //查一个
    User queryOne(String id);

    //查所有
    List<User> queryAll();

    //查各个性别 分布的 地区
    public List<City> queryAllSexCity(String sex);

    //查每个月  各个性别 的注册人数
    public List<Mc> queryAllMonth(String sex, Integer month);

}
