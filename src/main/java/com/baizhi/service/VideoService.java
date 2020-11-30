package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {

    //查所有    起始条数      结束条数
    List<Video> findVideo(Integer page, Integer rows);

    //查总条数
    Integer totalCounts();

    //添加
    public String addVideo(Video video);

    //上传到阿里云
    void upload(MultipartFile picImg, String id,HttpServletRequest request);
    //查一个
    Video queryOne(String id);
    //修改
    void update(Video video);
    //删除
    void delete(Video video);

    List<VideoPO>queryByReleaseTime();
}
