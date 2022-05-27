package com.item.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class LogTraceAspect {

    @Pointcut("execution(* com.item.config..*(..))")
    public void allConfigs() {}

    @Before("execution(* com.item..*(..)) && !allConfigs()")
    public void doLogTraceBefore(JoinPoint joinPoint) {
        log.info("[LogTrace][Before] {}", joinPoint.getSignature());
    }

    @After("execution(* com.item..*(..)) && !allConfigs()")
    public void doLogTraceAfter(JoinPoint joinPoint) {
        log.info("[LogTrace][After] {}", joinPoint.getSignature());
    }
}
