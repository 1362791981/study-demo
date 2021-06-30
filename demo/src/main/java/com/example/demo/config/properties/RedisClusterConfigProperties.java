package com.example.demo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfigProperties {

    private List<String> nodes;
    private Integer maxAttempts;
    private Integer maxRedirects;
    private Integer connectionTimeout;
    private Integer soTimeout;
    private String password;

}

