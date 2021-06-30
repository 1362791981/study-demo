package com.example.demo.study.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Description: 初始化一个线程池
 * @ClassName: ThreadPoolExecutorUtil
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-04 14:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class ThreadPoolExecutorUtil {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

    //配置拒绝策略 用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();


    public ExecutorService newThreadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueCapacity));
        executor.setRejectedExecutionHandler(rejectedExecutionHandler);
        return executor;
    }

    public ExecutorService newThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueCapacity));
        executor.setRejectedExecutionHandler(rejectedExecutionHandler);
        return executor;
    }

    public ExecutorService newThreadPoolExecutor(int corePoolSize, int maxPoolSize, RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueCapacity));
        executor.setRejectedExecutionHandler(rejectedExecutionHandler);
        return executor;
    }

    public ExecutorService newThreadPoolExecutor(int corePoolSize, int maxPoolSize, int keepAliveSeconds, TimeUnit timeUnit, BlockingDeque blockingDeque, RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, timeUnit, blockingDeque);
        executor.setRejectedExecutionHandler(rejectedExecutionHandler);
        return executor;
    }
}
