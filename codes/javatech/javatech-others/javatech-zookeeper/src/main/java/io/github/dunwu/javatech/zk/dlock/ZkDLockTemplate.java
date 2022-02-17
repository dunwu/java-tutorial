package io.github.dunwu.javatech.zk.dlock;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunyujia@aliyun.com on 2016/2/26.
 */
public class ZkDLockTemplate implements DLockTemplate {

    private static final Logger log = LoggerFactory.getLogger(ZkDLockTemplate.class);

    private CuratorFramework client;

    public ZkDLockTemplate(CuratorFramework client) {
        this.client = client;
    }

    @Override
    public <V> V execute(String lockId, long timeout, Callback<V> callback) {
        ZookeeperReentrantDistributedLock distributedReentrantLock = null;
        boolean getLock = false;
        try {
            distributedReentrantLock = new ZookeeperReentrantDistributedLock(client, lockId);
            if (tryLock(distributedReentrantLock, timeout)) {
                getLock = true;
                return callback.onGetLock();
            } else {
                return callback.onTimeout();
            }
        } catch (InterruptedException ex) {
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }

    private boolean tryLock(ZookeeperReentrantDistributedLock distributedReentrantLock, long timeout) {
        return distributedReentrantLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

}
