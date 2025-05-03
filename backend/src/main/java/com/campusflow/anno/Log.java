package com.campusflow.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)     // 限定注解仅能标注在方法上
@Retention(RetentionPolicy.RUNTIME)     // 设定注解的保留策略为运行时
public @interface Log { }
