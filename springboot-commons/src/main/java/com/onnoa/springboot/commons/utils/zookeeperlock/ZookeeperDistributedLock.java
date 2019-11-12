package com.onnoa.springboot.commons.utils.zookeeperlock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDistributedLock extends AbstractZookeeperLock {

    public ZookeeperDistributedLock(String lockName) {
        lock = lockName;
    }

    @Override
    protected boolean tryLock() {
        try {
            // 创建临时节点
            zkClient.createEphemeral(lock);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    protected void waitLock() {
        // 如果其他线程已经创建了临时节点，此线程只能进行等待，不能再创建临时节点
        // 如果该临时节点被删除，就可以创建临时节点

        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if(countDownLatch != null){
                    // 倒计数器减 1
                    countDownLatch.countDown();
                }
            }
        };

        // 订阅数据改变，监听已经获取到锁的节点
        zkClient.subscribeDataChanges(lock,listener);
        // 判断锁的节点是否存在
        if(zkClient.exists(lock)){
            // 创建倒计数器
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 取消订阅
        zkClient.unsubscribeDataChanges(lock,listener);
    }
}
