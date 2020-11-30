package com.baizhi.service;

import com.baizhi.entity.Sort;

import java.util.List;

public interface SortService {
    //查询一级类别
    public List<Sort> queryOne(Integer Levels,Integer page,  Integer rows);
    //查总条数
    Integer totalCounts(Integer levels);
    //查所有二级类别
    public List<Sort> queryTwo(Integer levels,String parentId,Integer begin,Integer rows);
    //查一级类别下二级类别下的数量
    public Integer count(String parentId);


    //添加一级类别
    void addOne(Sort sort);
    //查询所有的一级
    List<Sort> queryAllOne();
    //添加二级类别
    //void addTwo(Sort sort);

    //修改一级类别
    void updateOne(Sort sort);
    //删除
    String delete(String id);

    public List<Sort> queryAllCategory();

}
