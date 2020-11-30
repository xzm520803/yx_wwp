package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.Mc;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.management.Query;
import java.util.List;

public interface UserDAO {
    //查所有  起始条数                                  结束条数
    List<User> findAll(@Param("start") int start, @Param("rows") int rows);

    //查总条数
    Integer totalCounts();

    //修改状态
    void update(User user);
    User queryOne(String id);

    //查所有
    List<User>queryAll();

    //添加
    void add(User user);

    //查各个性别 分布的 地区
    public List<City> queryAllSexCity(String sex);

    //查每个月  各个性别 的注册人数
    public List<Mc> queryAllMonth(@Param("sex") String sex, @Param("month") Integer month);

}
