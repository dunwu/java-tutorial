package io.github.dunwu.distributed.id;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * ZK 分布式 ID
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-12-20
 */
@Slf4j
public class ZookeeperDistributedId {

    public static void main(String[] args) throws Exception {

        // 获取客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

        // 开启会话
        client.start();

        String id1 = client.create()
                           .creatingParentsIfNeeded()
                           .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                           .forPath("/zkid/id_");
        log.info("id: {}", id1);

        String id2 = client.create()
                           .creatingParentsIfNeeded()
                           .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                           .forPath("/zkid/id_");
        log.info("id: {}", id2);

        List<String> children = client.getChildren().forPath("/zkid");
        if (CollectionUtil.isNotEmpty(children)) {
            for (String child : children) {
                client.delete().forPath("/zkid/" + child);
            }
        }
        client.delete().forPath("/zkid");

        // 关闭客户端
        client.close();
    }

}
