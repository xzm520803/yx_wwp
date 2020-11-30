package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCache;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.City;
import com.baizhi.entity.Mc;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll(int page, int rows) {
        //page  当前页   rows 每页展示的记录数
        int begin = (page-1)*rows;
        int end = page*rows;

        List<User> all = userDAO.findAll(begin,end);
        return all;
    }

    @Override
    public Integer totalCounts() {
        Integer integer = userDAO.totalCounts();
        return integer;
    }


    //修改
    @DelCache("-----")
    @AddLog("修改用户")
    @Override
    public void update(User user) {
        userDAO.update(user);
    }
    //查一个
    @Override
    public User queryOne(String id) {
        return userDAO.queryOne(id);
    }

    //查所有
    @Override
    public List<User> queryAll() {
        List<User> users = userDAO.queryAll();
        return users;
    }

    @Override
    public List<City> queryAllSexCity(String sex) {
        List<City> lists = userDAO.queryAllSexCity(sex);
        return lists;
    }

    @Override
    public List<Mc> queryAllMonth(String sex, Integer month) {
        return userDAO.queryAllMonth(sex, month);
    }
}
