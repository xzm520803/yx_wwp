package com.baizhi.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class AliyunOSSUtil {


    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static String accessKeyId = "LTAI4GEJMosrHGYmVScEyZFY";
    private static String accessKeySecret = "dHpjHOZnUTGMRC39hjSdlm50j1hccb";

    /*
    * 上传视频至阿里云
    * 参数:
    *   videoPath: MultipartFile类型的文件
    *   bucketName:存储空间名
    *   objectName:文件名
    * */
    public static void uploadFileByte(MultipartFile videoPath,String bucketName,String objectName){

        //转为字节数组
        byte[] bytes = new byte[0];
        try {
            bytes = videoPath.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

        // 上传Byte数组。
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();
    }



    /*
     * 截取视频第一帧
     * 参数:
     *   bucketName:存储空间名
     *   videoName:视频名
     *   coverName:封面名
     * */
    public static void interceptVideoCover(String bucketName,String videoName,String coverName){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置视频截帧操作。
        String style = "video/snapshot,t_1000,f_jpg,w_800,h_600";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, videoName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println(signedUrl);

        //上传封面图片
        uploadFileNetIO(bucketName,coverName,signedUrl.toString());

        //关闭OSSClient。
        ossClient.shutdown();
    }


    /*
     * 上传网络文件
     * 参数:
     *   bucketName:存储空间名
     *   objectName:文件名
     *   signedUrl:网络路径
     * */
    public static void uploadFileNetIO(String bucketName,String objectName,String signedUrl){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL(signedUrl).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /*
     * 删除文件
     * 参数:
     *   bucketName:存储空间名
     *   objectName:文件名
     * */
    public static void deleteFile(String bucketName,String objectName){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();

    }
}
