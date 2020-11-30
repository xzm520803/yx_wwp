package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Emp;
import com.baizhi.entity.Teacher;
import com.baizhi.entity.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EasyPoiTests {

    @Autowired
    UserDAO userDAO;

    @Test
    public void test2() {

        //导入设置的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //标题所占行
        params.setHeadRows(1);   //表头所占行

        //导入
        List<Emp> teachers = ExcelImportUtil.importExcel(new File("F:\\cf\\totls.xls"), Emp.class, params);

        teachers.forEach(teacher -> System.out.println(teacher));


    }

    @Test
    public void Poiexport(){

//        ArrayList<Emp> emps = new ArrayList<>();
////
////        emps.add(new Emp("1","src/main/webapp/imgs/1.jpg",18,new Date()));
////        emps.add(new Emp("2","src/main/webapp/imgs/1.jpg",30,new Date()));
////        emps.add(new Emp("3","src/main/webapp/imgs/1.jpg",18,new Date()));
////        emps.add(new Emp("4","src/main/webapp/imgs/1.jpg",23,new Date()));
////
////        ArrayList<Teacher> teachers = new ArrayList<>();
////
////        teachers.add(new Teacher("11","suns",emps));
////        teachers.add(new Teacher("12","xiaochen",emps));
        //查询数据库信息
        List<User> all = userDAO.queryAll();

        //导出设置的参数  参数:大标题,工作表名
        ExportParams exportParams = new ExportParams("计算机一班学生", "学生");

        //导出工具   参数:导出的参数,对应的实体类,导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams,User.class, all);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("F:\\cf\\用户.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
