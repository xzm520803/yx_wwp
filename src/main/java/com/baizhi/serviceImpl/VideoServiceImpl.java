package com.baizhi.serviceImpl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCache;
import com.baizhi.dao.VideoDAO;
import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDAO videoDAO;

    @Resource
    HttpServletRequest request;


    @Override
    public List<Video> findVideo(Integer page, Integer rows) {
        //  page当前页    rows结束条数
        Integer begin = (page-1)*rows;
        Integer end = page*rows;

        List<Video> video1 = videoDAO.findVideo(begin,end);
        return video1;
    }

    @Override
    public Integer totalCounts() {
        Integer integer = videoDAO.totalCounts();
        return integer;
    }

    @DelCache("-----")
    @AddLog("添加视频")
    @Override
    public String addVideo(Video video) {
        video.setId(UUID.randomUUID().toString());
        video.setLike_count(0);
        video.setPlay_count(0);
        video.setStatus(1);
        video.setUpload_time(new Date());
        videoDAO.addVideo(video);

        return video.getId();
    }


    @DelCache("-----")
    @AddLog("上传视频到阿里云")
    @Override
    public void upload(MultipartFile video_path, String id,HttpServletRequest request) {
        //获取文件名
        String filename = video_path.getOriginalFilename();
        //拼接时间戳    1606185263426-草原.mp4
        String newName=new Date().getTime()+"-"+filename;
        //拼接视频文件夹
        String videoName="video/"+newName;
        /*
         * 上传视频至阿里云
         * 参数:
         *   videoPath: MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadFileByte(video_path,"aihuazhuya",videoName);
        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";
        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名  文件夹
         *   coverName:封面名
         * */
        AliyunOSSUtil.interceptVideoCover("aihuazhuya", videoName,coverName);

        //5.数据修改
        Video video = new Video();
        video.setId(id);
        //阿里云视频存储地址
        video.setVideo_path("https://aihuazhuya.oss-cn-beijing.aliyuncs.com/"+videoName);
        //阿里云图片存储地址
        video.setCover_path("http://aihuazhuya.oss-cn-beijing.aliyuncs.com/"+coverName);
        videoDAO.update(video);

    }

    @Override
    public Video queryOne(String id) {
        return videoDAO.queryOne(id);
    }

    @Override
    public void update(Video video) {}

    @DelCache("-----")
    @AddLog("删除用户视频")
    @Override
    public void delete(Video video) {
        //根据id查询数据
        Video videos = videoDAO.queryOne(video.getId());

        String videoPath = videos.getVideo_path().replace("https://aihuazhuya.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = videos.getCover_path().replace("http://aihuazhuya.oss-cn-beijing.aliyuncs.com/", "");

        //2.删除视频   432425435-xxx.mp4
        AliyunOSSUtil.deleteFile("aihuazhuya",videoPath);
        //3.删除封面
        AliyunOSSUtil.deleteFile("aihuazhuya",coverPath);
        //1.删除数据
        videoDAO.delete(video);
    }

    @Override
    public List<VideoPO> queryByReleaseTime() {
        List<VideoPO> videoPOList = videoDAO.queryByReleaseTime();

        for (VideoPO videoPO : videoPOList){
            //获取视频id
            String id = videoPO.getId();

            //根据视频id  redis查询点赞数
            videoPO.setLikeCount(8);
        }
        return videoPOList;
    }
}
