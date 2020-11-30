package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    //登陆
    String queryOne(Admin admin, HttpServletRequest request, String clientCode);

}
