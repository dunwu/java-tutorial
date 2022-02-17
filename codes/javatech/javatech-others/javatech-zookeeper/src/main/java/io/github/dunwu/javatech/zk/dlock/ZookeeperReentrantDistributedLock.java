package io.github.dunwu.javatech.zk.dlock;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 基于Zookeeper的可重入互斥锁(关于重入:仅限于持有zk锁的jvm内重入) Created by sunyujia@aliyun.com on 2016/2/24.
 */
public class ZookeeperReentrantDistributedLock implements DistributedLock {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperReentrantDistributedLock.class);

    /**
     * 线程池
     */
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

    /**
     * 所有PERSISTENT锁节点的根位置
     */
    public static final String ROOT_PATH = "/distributed_lock/";

    /**
     * 每次延迟清理PERSISTENT节点的时间  Unit:MILLISECONDS
     */
    private static final long DELAY_TIME_FOR_CLEAN = 1000;

    /**
     * zk 共享锁实现
     */
    private InterProcessMutex interProcessMutex;

    /**
     * 锁的ID,对应zk一个PERSISTENT节点,下挂EPHEMERAL节点.
     */
    private String path;

    /**
     * zk的客户端
     */
    private CuratorFramework client;

    public ZookeeperReentrantDistributedLock(CuratorFramework client, String lockId) {
        this.client = client;
        this.path = ROOT_PATH + lockId;
        interProcessMutex = new InterProcessMutex(this.client, this.path);
    }

    @Override
    public void lock() {
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) {
        try {
            return interProcessMutex.acquire(timeout, unit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void unlock() {
        try {
            interProcessMutex.release();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            executorService.schedule(new Cleaner(client, path), DELAY_TIME_FOR_CLEAN, TimeUnit.MILLISECONDS);
        }
    }

    static class Cleaner implements Runnable {

        private String path;

        private CuratorFramework client;

        public Cleaner(CuratorFramework client, String path) {
            this.path = path;
            this.client = client;
        }

        @Override
        public void run() {
            try {
                List<String> list = client.getChildren().forPath(path);
                if (CollectionUtil.isEmpty(list)) {
                    client.delete().forPath(path);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }

}
