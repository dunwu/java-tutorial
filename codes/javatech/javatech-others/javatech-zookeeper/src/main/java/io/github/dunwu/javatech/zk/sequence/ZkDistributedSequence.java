package io.github.dunwu.javatech.zk.sequence;

import io.github.dunwu.javatech.zk.dlock.ZkReentrantLockCleanerTask;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.LoggerFactory;

/**
 * Created by sunyujia@aliyun.com on 2016/2/25.
 */
public class ZkDistributedSequence implements DistributedSequence {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ZkReentrantLockCleanerTask.class);

    private CuratorFramework client;

    /**
     * Curator RetryPolicy maxRetries
     */
    private int maxRetries = 3;

    /**
     * Curator RetryPolicy baseSleepTimeMs
     */
    private final int baseSleepTimeMs = 1000;

    public ZkDistributedSequence(String zookeeperAddress) {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
            client = CuratorFrameworkFactory.newClient(zookeeperAddress, retryPolicy);
            client.start();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } catch (Throwable ex) {
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
        }
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public Long sequence(String sequenceName) {
        try {
            int value = client.setData().withVersion(-1).forPath("/" + sequenceName, "".getBytes()).getVersion();
            return new Long(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
