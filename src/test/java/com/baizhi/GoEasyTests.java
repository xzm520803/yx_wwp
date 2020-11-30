package com.baizhi;

import com.alibaba.fastjson.JSON;
//import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
//import com.baizhi.entity.UserExample;
import io.goeasy.GoEasy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@SpringBootTest
class GoEasyTests {



    @Test
    void queryUser(){

        for (int i = 0; i < 20; i++) {

            Random random = new Random();

            HashMap<String, Object> map = new HashMap<>();

            map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));
            map.put("boys", Arrays.asList(random.nextInt(200), random.nextInt(600), random.nextInt(200), random.nextInt(400), random.nextInt(200), random.nextInt(200)));
            map.put("girls", Arrays.asList(random.nextInt(100), random.nextInt(300), random.nextInt(200), random.nextInt(100), random.nextInt(200), random.nextInt(200)));

            //将Map对象转化为Json字符串
            String jsonString = JSON.toJSONString(map);

            //创建GoEasy对象  参数:机房地区,appkey
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");

            //发送消息   参数:通道名称,消息内容
            goEasy.publish("2005-channel", jsonString);

            System.out.println("==发送成功==");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    @Test
    void queryPhone(){

        //创建GoEasy对象  参数:机房地区,appkey
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");

        //发送消息   参数:通道名称,消息内容
        goEasy.publish("2005-channel", "Hello, GoEasy! 2005");




    }



}
