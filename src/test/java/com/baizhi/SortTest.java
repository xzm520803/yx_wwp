package com.baizhi;


import com.baizhi.dao.SortDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortTest {

    @Autowired
    private SortDAO sortDAO;

    @Test
    public void testCategory(){
        Integer integer = sortDAO.queryCategoryCounts("1");
        System.out.println(integer);
    }


}
