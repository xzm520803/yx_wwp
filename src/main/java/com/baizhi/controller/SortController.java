package com.baizhi.controller;


import com.baizhi.entity.Sort;
import com.baizhi.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("sortController")
@RequestMapping("sort")
public class SortController {
    @Autowired
    private SortService sortService;

    @RequestMapping("queryOne")
    @ResponseBody
    public Map<String,Object> queryOne(Integer page, Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        List<Sort> sorts = sortService.queryOne(1,page, rows);
        //总条数
        Integer counts = sortService.totalCounts(1);

        Integer total = counts%rows==0?counts/rows:counts/rows+1;
        map.put("page",page);
        map.put("total",total);
        map.put("counts",counts);
        map.put("rows",sorts);
        return map;
    }

    //分页查二级类别
    @RequestMapping("queryTwo")
    @ResponseBody
    public Map<String, Object> queryTwo(String parentId,Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        List<Sort> byPage = sortService.queryTwo(2,parentId, page, rows);
        map.put("rows", byPage);  //每页显示的数据

        byPage.forEach(cate-> System.out.println(cate));

        Integer count = sortService.count(parentId);  //总条数
        map.put("records", count);

        Integer total;
        if (count % rows == 0) {
            total = count / rows;
        } else {
            total = count / rows + 1;
        }
        map.put("total", total);
        map.put("page", page);
        return map;
    }

    //添加一级类别
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(Sort sort,String oper){
        HashMap<String, Object> map = new HashMap<>();
        if(oper.equals("add")){
            sortService.addOne(sort);
        }
        if(oper.equals("edit")){
            sortService.updateOne(sort);
        }
        if(oper.equals("del")){
            String message = sortService.delete(sort.getId());
            System.out.println(message);
            map.put("message", message);
        }
        return map;
    }

    //查询所有一级
    @RequestMapping("queryAllOne")
    public void queryAllOne(HttpServletResponse response){
        //查询所有的一级类别
        List<Sort> queryAllOne = sortService.queryAllOne();
        //html <select><option value=1>奥斯</option>...</select>
        StringBuilder builder = new StringBuilder();
        builder.append("<select>");
        //遍历  构建option标签
        queryAllOne.forEach(sort -> {
            builder.append("<option value="+sort.getId()+">"+sort.getCate_name()+"</option>");
        });
        builder.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            response.getWriter().print(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
