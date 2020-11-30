package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDAO {
    //登陆
    Admin queryOne(String username);
}
