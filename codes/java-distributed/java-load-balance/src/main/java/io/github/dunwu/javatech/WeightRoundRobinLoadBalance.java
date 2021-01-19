package io.github.dunwu.javatech;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author peng.zhang
 * @date 2021/1/19
 */
public class WeightRoundRobinLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final AtomicInteger position = new AtomicInteger(0);

    @Override
    protected N doSelect(List<N> nodes, String ip) {

        int length = nodes.size();

        List<N> serverList = new ArrayList<>();
        for (N node : nodes) {
            int weight = node.getWeight();
            for (int i = 0; i < weight; i++) {
                serverList.add(node);
            }
        }

        // 如果位置值已经等于节点数，重置为 0
        position.compareAndSet(length, 0);
        N node = serverList.get(position.get());
        position.getAndIncrement();
        return node;
    }

}
