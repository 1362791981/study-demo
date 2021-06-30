package com.example.demo.study.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 线程池参数实体类
 * @ClassName: AsyncTaskProperties
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-05-18 10:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
