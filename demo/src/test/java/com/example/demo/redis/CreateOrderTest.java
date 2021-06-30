package com.example.demo.redis;

import com.example.demo.study.HighConcurrency.service.CreateOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 创建订单测试
 * @ClassName: CreateOrderTest
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 22:12
 */
@SpringBootTest
public class CreateOrderTest {

    @Autowired
    CreateOrderService createOrderService;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void createOrder() throws IOException {

        String productId = "12003";
        redisTemplate.delete(productId);

        Object stock = redisTemplate.opsForHash().get(productId, "stock");
//        redisTemplate.opsForHash().put(productId, "stock", 10000);
        //核心线程数16，最大线程20，使用无界阻塞队列（注意内存溢出）的有界阻塞队列
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20000));
        for (int i = 1; i < 10002; i++) {
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    createOrderService.createOrder(String.valueOf(finalI), 1, productId);
                }
            });
        }
        executor.shutdown();

        //阻塞主线程
        System.in.read();
    }

}
