package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Controller("adminController")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    public String login(Admin admin, HttpServletRequest request,String clientCode) {
        String message = adminService.queryOne(admin, request, clientCode);
        request.getSession().setAttribute("message", message);
        if (message.equals("success")) {
            return "main/main";
        } else {
            return "redirect:/login/login.jsp";
        }
    }

    //验证码
    @RequestMapping("img")
    public String creatImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //调用验证码插件中的方法，生成验证图片
        String code = ImageCodeUtil.getSecurityCode();
        System.out.println(code);
        BufferedImage image = ImageCodeUtil.createImage(code);

        //使用相应输出流把图片输出到client
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image,"gif",os);

        //存到session中
        request.getSession().setAttribute("code",code);
        return null;

    }

    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        request.getSession().invalidate();//清楚所有session
        return "redirect:/login/login.jsp";
    }

}
