package com.item.aop;

import com.item.domain.log.LogTrace;
import com.item.domain.log.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("execution(* com.item.web..*(..))")
    public Object doLogTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
