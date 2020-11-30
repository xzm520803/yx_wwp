package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.baizhi.entity.City;
import com.baizhi.entity.Mc;
import com.baizhi.entity.Sc;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userController")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("queryMonthSex")
    public HashMap<String, Object> queryMonthSex(){
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<Object> monthList = new ArrayList<>();
        ArrayList<Object> boyList = new ArrayList<>();
        ArrayList<Object> girlList = new ArrayList<>();

        for (int i = 1 ; i<=12;i++){
            monthList.add(i+"月");
            //所有男生的  各个月份的注册人数
            Integer counts = 0;
            List<Mc> man = userService.queryAllMonth("男", i);
            for (Mc mc : man){
                counts = mc.getCounts();
            }
            boyList.add(counts);
            //所有女生的  各个月份的注册人数
            List<Mc> girl = userService.queryAllMonth("女", i);
            Integer counts1 = 0;
            for (Mc mc : girl){
                counts1 = mc.getCounts();
            }
            girlList.add(counts1);
        }

            map.put("month",monthList);
            map.put("boys",boyList);
            map.put("girl",girlList);

        String jsonString = JSON.toJSONString(map);

        //创建goEasy初始化对象  参数1：REST Host: 机房地区 zppkey:  应用appkey
        GoEasy goEasy  = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-d8c82401a6b443348f0ef93d03b1e2ab");
        //发送消息  参数：通道名称  消息的内容
        goEasy.publish("2005_channe1",jsonString);

        return map;
    }

    // 查各个性别  各个地区的注册人数
    @ResponseBody
    @RequestMapping("querySexCity")
    public ArrayList<Sc> querySexCity(){

        ArrayList<Sc> sc = new ArrayList<>();
        //所有的各个地区的男生注册人数
        List<City> cities = userService.queryAllSexCity("男");
        sc.add(new Sc("小男孩",cities));
        //所有的各个地区女生的注册人数
        List<City> citie = userService.queryAllSexCity("女");
        sc.add(new Sc("小女孩",citie));

    return sc;
    }


    @RequestMapping("findAll")
    @ResponseBody
    public HashMap<String,Object> findAll(Integer page, Integer rows){
        List<User> users;
        HashMap<String, Object> map = new HashMap<>();
        //总记录数
        Integer counts;
            //分页查询的结果
            users = userService.findAll(page, rows);
            //总条数
            counts = userService.totalCounts();
        int total = counts%rows==0?counts/rows:counts/rows+1;

        map.put("page",page);//当前页数
        map.put("total",total);//总页数
        map.put("counts",counts);//总记录数
        map.put("rows",users);//每页显示的数据

        return map;

    }


    //修改
    @RequestMapping("update")
    @ResponseBody
    public String update(User user) {
        User user1 = userService.queryOne(user.getId());
        String a = null;

        if (user1.getState().equals("0")){
            user1.setState("1");
            a = "1";
        }else{
            user1.setState("0");
            a = "0";
        }
        System.out.println(user1);
        userService.update(user1);
        return a;
    }

    //发送验证码
    @RequestMapping("phoneCode")
    @ResponseBody
    public Map<String,Object> phoneCode(String phones){
        String phoneMsg = null;
        Map<String,Object> map = new HashMap<>();
        try {
            //随机获取验证码
            String code = ImageCodeUtil.getSecurityCode();
            //调用工具类发送验证码
            phoneMsg = AliyunUtil.sendPhoneMsg(phones,code);

            map = new HashMap<>();
            map.put("message",phoneMsg);
            map.put("status","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message",phoneMsg);
            map.put("status","201");
        }
        return map;
    }
    //导出信息
    @RequestMapping("Poiexport")
    @ResponseBody
    public void Poiexport(HttpServletRequest request){

        List<User> users = userService.queryAll();

        String realPath = request.getServletContext().getRealPath("imgs");

        for (User user : users) {
            String img = user.getHead();
            user.setHead(realPath + "\\" + img );
        }
        //导出设置的参数  参数:大标题,工作表名
        ExportParams exportParams = new ExportParams("用户表", "用户");
        //导出工具   参数:导出的参数,对应的实体类,导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class, users);
        //导出
        try {
            workbook.write(new FileOutputStream(new File("F:\\cf\\用户.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导入信息
    @ResponseBody
    @RequestMapping("importExcel")
    public void importExcel() {
        //导入设置的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //标题所占行
        params.setHeadRows(1);   //表头所占行

//        //导入
//        List<User> users = ExcelImportUtil.importExcel(new File("F:\\Beijing\\后期项目\\导入.xls"), User.class, params);
//        for (User user : users) {
//            userService.(user);
//        }
//    }
    }
}