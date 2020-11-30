package com.baizhi;

import com.baizhi.dao.SortDAO;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;
import java.util.List;

@SpringBootTest
class YxWwpApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId("1");
        user.setName("www");
        user.setBrief("222");
        user.setHead("2");
        user.setIphone("111");
        user.setScore(55.0);
        user.setState("1");
        user.setCreateDate(new Date());
        userService.update(user);

    }


    @Autowired
    private SortDAO sortDAO;




    @Test
    public void counts(){
        Integer integer = sortDAO.queryCategoryCounts("1");
        System.out.println(integer);
    }

}
