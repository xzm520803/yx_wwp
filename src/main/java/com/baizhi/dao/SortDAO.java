package com.baizhi.dao;

import com.baizhi.entity.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SortDAO {

    //查询一级类别
    public List<Sort> queryOne(@Param("levels") Integer levels,@Param("start")Integer start, @Param("rows") Integer rows);

    //查总条数
    Integer totalCounts(Integer levels);


    //查所有二级类别
    public List<Sort> queryTwo(@Param("levels") Integer levels,@Param("id") String id,@Param("begin") Integer begin,@Param("rows") Integer rows);

    //查一级类别下二级类别下的数量
     Integer count(String id);



    //添加一级类别
    void addOne(Sort sort);
    //查询所有的一级
    List<Sort> queryAllOne(Integer levels);
    //添加二级类别
    void addTwo(Sort sort);

    //修改一级类别
    void updateOne(Sort sort);
    //删除
    void delete(String id);

    Integer queryCategoryCounts(@Param("id") String id);

    public List<Sort> queryAllCategory();



}
