package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {
    @Autowired
    private LogService logService;

    //查所有
    @RequestMapping("queryLog")
    @ResponseBody
    public Map<String,Object> queryLog(Integer page, Integer rows){
        List<Log> logs;
        HashMap<String, Object> map = new HashMap<>();
        //总记录数
        Integer counts;
        //分页查询的结果
        logs = logService.findLog(page, rows);
        //总条数
        counts = logService.totalCounts();
        int total = counts%rows==0?counts/rows:counts/rows+1;

        map.put("page",page);//当前页数
        map.put("total",total);//总页数
        map.put("records",counts);//总记录数
        map.put("rows",logs);//每页显示的数据

        return map;
    }

}
