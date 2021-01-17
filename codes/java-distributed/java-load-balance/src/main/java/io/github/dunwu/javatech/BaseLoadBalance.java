package io.github.dunwu.javatech;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2021-01-18
 */
public abstract class BaseLoadBalance<N extends Node> implements LoadBalance<N> {

    protected List<N> nodes = new LinkedList<>();

    @Override
    public void buildNodes(final Collection<N> collection) {
        this.nodes = new LinkedList<>(collection);
    }

    @Override
    public void addNode(N node) {
        this.nodes.add(node);
    }

    @Override
    public void removeNode(N node) {
        this.nodes.remove(node);
    }

    @Override
    public N select() {
        if (CollectionUtil.isEmpty(nodes)) {
            return null;
        }

        // 如果 nodes 列表中仅有一个 node，直接返回即可，无需进行负载均衡
        if (nodes.size() == 1) {
            return nodes.get(0);
        }

        return doSelect();
    }

    protected abstract N doSelect();

}
