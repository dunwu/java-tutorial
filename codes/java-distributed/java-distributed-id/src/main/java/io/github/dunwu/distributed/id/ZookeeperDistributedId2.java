package io.github.dunwu.distributed.id;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * ZK 分布式 ID
 * <p>
 * 基于原子计数器生成 ID
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-12-20
 */
@Slf4j
public class ZookeeperDistributedId2 {

    public static void main(String[] args) throws Exception {

        // 获取客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        DistributedAtomicLong atomicLong = new DistributedAtomicLong(client, "/zkid", retryPolicy);

        // 开启会话
        client.start();

        // 基于原子计数器生成 ID
        AtomicValue<Long> id1 = atomicLong.increment();
        log.info("id: {}", id1.postValue());

        AtomicValue<Long> id2 = atomicLong.increment();
        log.info("id: {}", id2.postValue());

        // 清理节点
        client.delete().forPath("/zkid");

        // 关闭客户端
        client.close();
    }

}
