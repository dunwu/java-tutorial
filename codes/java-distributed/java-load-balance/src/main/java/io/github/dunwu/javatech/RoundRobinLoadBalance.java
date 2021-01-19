package io.github.dunwu.javatech;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (加权)轮询负载均衡策略
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-20
 */
public class RoundRobinLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final AtomicInteger position = new AtomicInteger(0);

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        int length = nodes.size();
        // 如果位置值已经等于节点数，重置为 0
        position.compareAndSet(length, 0);
        N node = nodes.get(position.get());
        position.getAndIncrement();
        return node;
    }

}
