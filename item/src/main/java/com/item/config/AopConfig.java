package com.item.config;

import com.item.aop.LogTraceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect() {
        return new LogTraceAspect();
    }
}
