package com.baizhi.app;

import com.baizhi.common.CommonResult;
import com.baizhi.entity.Sort;
import com.baizhi.po.VideoPO;
import com.baizhi.service.SortService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunUtil;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    private VideoService videoService;

    @Resource
    private SortService sortService;

    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone){

        //生成验证码
        String randomCode = ImageCodeUtil.getSecurityCode();
        System.out.println("验证码："+randomCode);

        String message =null;
        try {
            //发送手机验证码
            message = AliyunUtil.sendPhoneMsg(phone,randomCode);
            return new CommonResult().success(message,phone);
        }catch (Exception e){
            return new CommonResult().failed(message);
        }
    }

    //首页展示视频
    @RequestMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime(){

        System.out.println("武斌傻逼");
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        }catch (Exception e){
            return new CommonResult().failed();
        }
    }

    //前台查所有类别   一级 /  二级
    @RequestMapping("queryAllCategory")
    public Object queryAllCategory(){

        try {
            List<Sort> sortList = sortService.queryAllCategory();
            return new CommonResult().success("请求成功",sortList);
        }catch (Exception e){
            return new CommonResult().failed("请求失败");
        }
    }
}
