package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCache;
import com.baizhi.dao.SortDAO;
import com.baizhi.entity.Sort;
import com.baizhi.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("sortService")
@Transactional
public class SortServiceImpl implements SortService {

    @Autowired
    private SortDAO sortDAO;
    //一级类别
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Sort> queryOne(Integer Levels, Integer page, Integer rows) {
        //page  当前页   rows 每页展示的记录数
        Integer begin = (page-1)*rows;
        return sortDAO.queryOne(Levels, begin, rows);
    }
    //一级类别的个数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer totalCounts(Integer levels) {

        return sortDAO.totalCounts(levels);
    }
    //查所有二级类别
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Sort> queryTwo(Integer levels,String parentId,Integer page, Integer rows) {
        Integer begin1 = (page - 1) * rows;
        return sortDAO.queryTwo(levels, parentId, begin1, rows);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    //查一级类别下二级类别下的数量
    @Override
    public Integer count(String parentId) {
        return sortDAO.count(parentId);
    }


    //添加一级类别
    @DelCache("-----")
    @AddLog("添加成功")
    @Override
    public void addOne(Sort sort) {
        if(sort.getParent_id()!=null){
            sort.setId(UUID.randomUUID().toString());
            sort.setLevels(2);
            sortDAO.addTwo(sort);
        }else {
            sort.setId(UUID.randomUUID().toString());
            sort.setParent_id(null);
            sort.setLevels(1);
            sortDAO.addOne(sort);
        }
    }


    //查询所有一级类别
    @Override
    public List<Sort> queryAllOne() {
        List<Sort> sorts = sortDAO.queryAllOne(1);
        return sorts;
    }



    //修改一级类别
    @DelCache("-----")
    @AddLog("修改一级类别")
    @Override
    public void updateOne(Sort sort) {
        sortDAO.updateOne(sort);
    }


    //删除
    @DelCache("-----")
    @AddLog("删除成功")
    @Override
    public String delete(String id) {
        String message;
        Integer count = sortDAO.queryCategoryCounts(id);  //
        if(count == 0){
            sortDAO.delete(id);
            message = "删除成功";

        }else{
            message = "该类别下有二级类别  无法删除！！！";
        }
        return message;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Sort> queryAllCategory() {
        List<Sort> sorts = sortDAO.queryAllCategory();
        sorts.forEach(u -> System.out.println(u));
        return sorts;
    }
}
