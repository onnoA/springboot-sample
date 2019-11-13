package com.onnoa.springboot.commons.utils.zookeeperlock;


import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractZookeeperLock implements IZookeeperLock {

    protected String zkAddress = "127.0.0.1:2181";

    protected ZkClient zkClient = new ZkClient(zkAddress);

    protected String lock = "/";

    // 倒计数器
    protected CountDownLatch countDownLatch;

    @Override
    public final void lock() {
        // 尝试获取锁
        if(tryLock()){
            // 获取锁成功
            System.out.println("获取锁成功.......");
        }else{
            // 尝试获取锁未成功，等待获取锁，此时阻塞，如果不阻塞，则可以往下执行代码
            waitLock();

            // 阻塞结束，重新进行获取锁
            lock();
        }


    }

    @Override
    public final void unlock() {
        // 释放锁 关闭连接自动释放锁
        if(zkClient != null){
            zkClient.close();
            System.out.println("释放锁成功...............");
        }

    }

    // 尝试获取锁
    protected abstract boolean tryLock();

    // 等待获取锁
    protected abstract void waitLock();
}
