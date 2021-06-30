package com.example.demo.study.HighConcurrency.service;

import com.example.demo.study.HighConcurrency.center.BadCreateOrderException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 模拟下单方法
 * @ClassName: CreateOrderService
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 18:40
 */
@Slf4j
@Service
public class CreateOrderService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createOrder(String orderSn, int number, String productId) {

        //log.info("订单编号：{}", orderSn);
        //创建订单
        //扣减库存
        deductionStock(productId, number);

    }

    /**
     * 扣减库存
     * 使用redis的原子自增方法保证扣减库存正确
     * 使用redisson分布式锁保证更新redis时 只有一个线程先去从数据库取数据更新到redis中
     * Rlock中再次判断当前商品是否已加入redis缓存
     *
     * @param productId
     * @param number
     */
    public void deductionStock(String productId, int number) {

        if (redisTemplate.opsForHash().get(productId, "stock") != null) {
            Long stock = redisTemplate.opsForHash().increment(productId, "stock", ~(number - 1));
            if (stock < 0) {
                redisTemplate.opsForHash().increment(productId, "stock", number);
                log.info("库存不足 stock:{}", stock);
                throw new BadCreateOrderException("库存不足");
            }
            if (stock == 0) {
                log.info("扣减库存成功 扣减数量:{} , 剩余数量：{}", number, stock);
            }

        } else {
            RLock rLock = redissonClient.getLock("stock_key_" + productId);
            try {
                rLock.lock();
                log.info("商品加锁 商品id:{}", productId);
                // 这里使用类似双重检测机制，比如同一时刻有5个线程要获取锁，4个等待，一个拿到了
                // 取到锁的线程执行完之后释放锁，其余等待线程会依次取到锁，为了避免重复从DB数据库查询更新缓存，这里就再判断一次
                if (redisTemplate.opsForHash().get(productId, "stock") == null) {
                    redisTemplate.opsForHash().put(productId, "stock", 10000);
                    log.info("更新redis 商品id:{} 更新后内容：{}", productId, redisTemplate.opsForHash().get(productId, "stock"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rLock.unlock();
                log.info("释放锁");
            }
            deductionStock(productId, number);
        }
    }
}
