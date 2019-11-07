package com.onnoa.chapter03.springboot.redission.redissionlock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 17:06
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}
