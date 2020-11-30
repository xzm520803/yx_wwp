package com.baizhi.entity;


import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable {
    private String id;
    private String title;        //标题
    private String brief;        //简介
    private String cover_path;   //封面
    private String video_path;   //视频
    private Date upload_time;    //上传时间
    private Integer like_count;  //点赞数
    private Integer play_count;  //播放数
    private String category_id;      //类别id
    private String user_id;      //用户id
    private String group_id;     //分组id
    private Integer status;      //状态

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", cover_path='" + cover_path + '\'' +
                ", video_path='" + video_path + '\'' +
                ", upload_time=" + upload_time +
                ", like_count=" + like_count +
                ", play_count=" + play_count +
                ", category_id='" + category_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", group_id='" + group_id + '\'' +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public Integer getPlay_count() {
        return play_count;
    }

    public void setPlay_count(Integer play_count) {
        this.play_count = play_count;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Video() {
    }

    public Video(String id, String title, String brief, String cover_path, String video_path, Date upload_time, Integer like_count, Integer play_count, String category_id, String user_id, String group_id, Integer status) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.cover_path = cover_path;
        this.video_path = video_path;
        this.upload_time = upload_time;
        this.like_count = like_count;
        this.play_count = play_count;
        this.category_id = category_id;
        this.user_id = user_id;
        this.group_id = group_id;
        this.status = status;
    }
}
