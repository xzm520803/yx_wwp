package com.baizhi.annotcation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//指定该注解在方法上生效
@Retention(RetentionPolicy.RUNTIME)//指定运行时生效
public @interface AddLog {
    String value() default "";
}
