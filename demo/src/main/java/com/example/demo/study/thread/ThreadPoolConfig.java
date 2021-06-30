package com.example.demo.study.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 线程池配置类
 * @ClassName: AsyncTaskProperties
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-18 10:46
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class ThreadPoolConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.MINUTES, new LinkedBlockingQueue<>(queueCapacity));
        //配置拒绝策略 用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return executor;
    }
}
