package com.item.config;

import com.item.aop.LogTraceAspect;
import com.item.domain.log.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AopConfig {

    private final LogTrace logTrace;

    @Bean
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect(logTrace);
    }
}
