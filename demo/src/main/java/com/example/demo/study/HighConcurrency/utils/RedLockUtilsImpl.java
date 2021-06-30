package com.example.demo.study.HighConcurrency.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: RedLock实现类
 * @ClassName: RedLockUtilsImpl
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-06-29 18:20
 */
@Component
public class RedLockUtilsImpl implements RedLockUtils {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 可重入,!线程不主动解锁将会永远存在! 慎用
     * Acquires the lock.
     *
     * <p>If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     *
     * <p><b>Implementation Considerations</b>
     *
     * <p>A {@code Lock} implementation may be able to detect erroneous use
     * of the lock, such as an invocation that would cause deadlock, and
     * may throw an (unchecked) exception in such circumstances.  The
     * circumstances and the exception type must be documented by that
     * {@code Lock} implementation.
     */
    @Override
    public void lock(String lockKey) {
        RLock lock1 = redissonClient.getLock(lockKey);
        redissonClient.getRedLock(lock1).lock();
    }

    @Override
    public void lock(String lockKey, Runnable runnable) {
        lock(lockKey);
        try {
            runnable.run();
        } finally {
            this.unLock(lockKey);
        }
    }

    @Override
    public void lock(String lockKey, long leaseTime) {
        RLock lock1 = redissonClient.getLock(lockKey);
        redissonClient.getRedLock(lock1).lock(leaseTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public void lock(String lockKey, long leaseTime, Runnable runnable) {
        lock(lockKey, leaseTime);
        try {
            runnable.run();
        } finally {
            this.unLock(lockKey);
        }
    }

    @Override
    public boolean tryLockTimeout(String lockKey, long waitTime, long leaseTime) throws InterruptedException {
        RLock lock1 = redissonClient.getLock(lockKey);
        return redissonClient.getRedLock(lock1).tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean tryLockTimeout(String lockKey, long waitTime, long leaseTime, Runnable runnable) throws InterruptedException {
        boolean lock = tryLockTimeout(lockKey, waitTime, leaseTime);
        try {
            runnable.run();
        } finally {
            this.unLock(lockKey);
        }
        return lock;
    }

    @Override
    public void unLock(String lockKey) {
        RLock lock1 = redissonClient.getLock(lockKey);
        redissonClient.getRedLock(lock1).unlock();
    }

}
