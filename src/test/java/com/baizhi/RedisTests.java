package com.baizhi;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Set;

@SpringBootTest
public class RedisTests {


    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void queryPhone() {
        redisTemplate.opsForValue().get("*");

    }
}
