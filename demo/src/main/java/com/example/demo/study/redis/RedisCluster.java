package com.example.demo.study.redis;

import com.example.demo.study.thread.ThreadPoolExecutorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: redis Cluster 集群测试
 * @ClassName: RedisCluster
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-04 09:46
 */
@Component
public class RedisCluster {

    @Autowired
    RedisString redisString;

    public void redisString(){
        redisString.execute();
    }

}

@Component
@Slf4j
class RedisString {

    @Autowired
    ThreadPoolExecutorUtil threadPoolExecutorUtil;
    @Autowired
    RedisTemplate redisTemplate;

    //字符串
    @Transactional(propagation = Propagation.NEVER)
    public void execute() {

        //set，get
        String key = "product::1001";
        redisTemplate.opsForValue().set(key, "农夫山泉");
        Object value = redisTemplate.opsForValue().get(key);

        //1.LEN：O(1)获取字符串长度
        Long size = redisTemplate.opsForValue().size(key);
        log.info("1.LEN：O(1)获取字符串长度 key {} 的value为：{} 长度为：{}", key, value, size);

        //2.APPEND ：往字符串 append 内容，而且采用智能分配内存（每次2倍）
        Object appendValue = redisTemplate.opsForValue().append(key, "550ml");
        log.info("2.APPEND ：往字符串 append 内容，而且采用智能分配内存（每次2倍） key {} 的value为：{} 长度为：{}", key, appendValue, size);

        //3.设置和获取字符串的某一段内容
        String s = redisTemplate.opsForValue().get(key, 0, 2);
        log.info("3.设置和获取字符串的某一段内容 key {} 的value为：{}", key, s);

        //4.原子计数器
        String keyInteget = "keyInteget";
        //初始化一个线程池
        //CallerRunsPolicy主线程继续执行，用于保证计算正确性，
        ExecutorService executorService = threadPoolExecutorUtil.newThreadPoolExecutor(5, new ThreadPoolExecutor.CallerRunsPolicy());
        for (int t = 0; t < 5; t++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    {
                        for (int i = 0; i < 1000; i++) {
                            redisTemplate.opsForValue().decrement(keyInteget);
                        }
                    }
                }
            });
        }
        Long decrement = (Long) redisTemplate.opsForValue().get(keyInteget);
        log.info("4.原子计数器 key {} 的value为：{}", key, decrement);

        //5.GET SET 命令的妙用，请于清空旧值的同时设置一个新值，配合原子计数器使用

    }


}
