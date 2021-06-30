package com.example.demo.study.HighConcurrency.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Redission分布式锁配置文件
 * @ClassName: RedissonConfig
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 17:29
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

//    @Value("${spring.redis.password}")
    private String password = "";

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if (StringUtils.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        System.out.println("------------- redisson -----------------------");
        System.out.println(config.getTransportMode());
        ;
//        config.setTransportMode(TransportMode.EPOLL);
        return Redisson.create(config);
    }
}
