package com.onnoa.springboot.commons.utils.zookeeperlock;


public interface IZookeeperLock {

    // 加锁
    public void lock();

    // 释放锁
    public void unlock();
}
