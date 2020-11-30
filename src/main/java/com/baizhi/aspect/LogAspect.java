package com.baizhi.aspect;

import com.baizhi.annotcation.AddLog;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import jdk.net.SocketFlow;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAspect {

    @Resource
    private LogMapper logDAO;

    @Resource
    HttpServletRequest request;

    @Around("@annotation(com.baizhi.annotcation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){

        //谁在什么时间操作了什么 成功了没有
        //获取用户的数据
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解对应的属性值
        String value = addLog.value();

        String message;
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();    //放行成功
            message = "success";
        }catch (Throwable throwable){
            message = "error";
        }



        //Log log = new Log(UUID.randomUUID().toString(), admin.getUsername(), new Date(), methodName + "(" + value + ")", messagem, Status);
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());  //id
        log.setName(admin.getUsername());           //当前管理员
        log.setTimes(new Date());                   //什么时间
        log.setOptions(methodName+" {"+value+"}");   //执行了什么操作
        log.setStatus(message);                     //状态

        System.out.println("数据入库"+log);
        logDAO.insert(log);
        return result;
    }

}
