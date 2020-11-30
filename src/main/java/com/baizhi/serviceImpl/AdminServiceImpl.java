package com.baizhi.serviceImpl;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryOne(Admin admin, HttpServletRequest request, String clientCode) {
        String message = null;
        //获取验证码
        String code = (String) request.getSession().getAttribute("code");
        if(clientCode.equals(code)){
            Admin admin1 = adminDAO.queryOne(admin.getUsername());
            if(admin1!=null){
                if(admin1.getPassword().equals(admin.getPassword())){
                    HttpSession session = request.getSession();
                    session.setAttribute("admin",admin1);
                    message="success";
                }else{
                    message="密码不正确!";
                }
            }else{
                message="用户不存在!";
            }
        }else{
            message="验证码不正确!";
        }
        return message;
    }
}
