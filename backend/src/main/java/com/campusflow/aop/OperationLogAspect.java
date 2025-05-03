package com.campusflow.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.campusflow.mapper.OperateLogMapper;
import com.campusflow.pojo.OperateLog;
import com.campusflow.utils.CurrentHolder;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j          // 日志记录器
@Aspect         // 标记为切面类
@Component
public class OperationLogAspect {

    // 操作日志映射器，用于将操作日志保存到数据库
    @Autowired
    private OperateLogMapper operateLogMapper;
    
    /**
     * 环绕通知，用于记录操作日志
     */
    @Around("@annotation(com.campusflow.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行目标方法
        Object result = joinPoint.proceed();
        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 构建日志实体
        OperateLog olog = new OperateLog();
        // 这里需要你根据实际情况获取当前用户ID
        olog.setOperateEmpId(getCurrentUserId());

        // 记录操作时间
        olog.setOperateTime(LocalDateTime.now());   

        // 记录类名、方法名、参数、返回值、耗时
        olog.setClassName(joinPoint.getTarget().getClass().getName());

        // 记录方法名、参数、返回值、耗时
        olog.setMethodName(joinPoint.getSignature().getName());

        // 记录参数
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));

        // 记录返回值
        olog.setReturnValue(result != null ? result.toString() : "void");
        
        // 记录耗时
        olog.setCostTime(costTime);
        
        // 保存日志
        log.info("记录操作日志: {}", olog);

        // 保存日志到数据库
        operateLogMapper.insert(olog);

        return result;
    }

    // 从当前线程中获取当前用户ID
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}