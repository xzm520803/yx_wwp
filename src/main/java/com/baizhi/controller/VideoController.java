package com.baizhi.controller;

import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    //查所有
    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object>queryAll(Integer page, Integer rows){
        List<Video> videos;
        HashMap<String, Object> map = new HashMap<>();
        //总记录数
        Integer counts;
        //分页查询的结果
         //videos = videoService.findVideo(page, rows);
        videos = videoService.findVideo(page, rows);
        //总条数
        counts = videoService.totalCounts();
        int total = counts%rows==0?counts/rows:counts/rows+1;

        map.put("page",page);//当前页数
        map.put("total",total);//总页数
        map.put("records",counts);//总记录数
        map.put("rows",videos);//每页显示的数据

        return map;
    }

    //增删改查
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Video video,String oper){
        String s = null;
        if(oper.equals("add")){
            System.out.println(video);
            s = videoService.addVideo(video);
        }
        if(oper.equals("edit")){
            videoService.update(video);
        }
        if(oper.equals("del")){
            videoService.delete(video);
        }
        return s;
    }

    //文件上传
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile video_path, String id,HttpServletRequest request){
        videoService.upload(video_path,id,request);
    }

}
