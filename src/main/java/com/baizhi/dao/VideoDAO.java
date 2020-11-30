package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoDAO {
    //查所有  起始条数                                  结束条数
    List<Video> findVideo(@Param("start") Integer start, @Param("rows") Integer rows);
    //查总条数
    Integer totalCounts();

    //添加
    public void addVideo(Video video);
    //上传到阿里云
    void upload(MultipartFile picImg, String id);
    //查一个
    Video queryOne(String id);
    //修改
    void update(Video video);
    //删除
    void delete(Video video);

    List<VideoPO>queryByReleaseTime();
}
