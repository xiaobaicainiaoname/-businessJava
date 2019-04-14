package com.example.demo.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.SoftException;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class WebLogAspect {
 
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
 
   /* @Pointcut("execution(public * com.example.demo.controller.TestController.*(..))")
    public void webLog(){
    	System.out.println("begin===================================================");
    }
 
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
 
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + joinPoint.getArgs());
 
    }
 
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }*/
    
    @AfterThrowing(pointcut="execution(public * com.example.demo.controller.*.*(..))",throwing="a")
    public void afterThrowing(JoinPoint joinPoint,Throwable a) throws Throwable {
        // 处理完请求，返回内容
    	System.err.println("产生异常："+a.getMessage());
    	System.err.println("前台传参数，异常参数为："+Arrays.toString(joinPoint.getArgs()));
//        logger.info("RESPONSE : " + ret);
    }
 
}