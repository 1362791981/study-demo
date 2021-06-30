package com.example.demo.redis;

import com.example.demo.study.redis.RedisCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description: redis 测试类
 * @ClassName: Test
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-10 09:46
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisCommand redisCommand;

    @Test
    public void single(){
        redisCommand.execute();
    }
}
