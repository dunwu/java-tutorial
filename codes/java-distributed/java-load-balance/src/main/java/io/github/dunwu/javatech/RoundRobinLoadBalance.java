package io.github.dunwu.javatech;

import io.github.dunwu.tool.collection.CollectionUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * (加权)轮询负载均衡策略
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-20
 */
public class RoundRobinLoadBalance<V extends Node> implements LoadBalance<V> {

    private boolean weightMode;

    private AtomicInteger offset = new AtomicInteger(0);

    private Set<V> nodes = Collections.emptyNavigableSet();

    public RoundRobinLoadBalance() {
        this.weightMode = false;
    }

    public RoundRobinLoadBalance(boolean weightMode) {
        this.weightMode = weightMode;
    }

    @Override
    public void buildInList(final Collection<V> collection) {
        this.offset = new AtomicInteger(0);
        this.nodes = new LinkedHashSet<>(collection);
    }

    @Override
    public void addNode(V node) {
        this.nodes.add(node);
    }

    @Override
    public void removeNode(V node) {
        this.nodes.remove(node);
    }

    @Override
    public V next() {
        if (weightMode) {
            return getNextInWeightMode();
        } else {
            return getNextInNormalMode();
        }
    }

    private V getNextInWeightMode() {
        if (CollectionUtil.isEmpty(nodes)) {
            return null;
        }

        int totalWeight = nodes.stream().mapToInt(Node::getWeight).sum();
        int number = offset.getAndIncrement() % totalWeight;

        for (V node : nodes) {
            if (node.getWeight() > number) {
                return node;
            }
            number -= node.getWeight();
        }
        return null;
    }

    private V getNextInNormalMode() {
        if (CollectionUtil.isEmpty(this.nodes)) {
            return null;
        }

        int size = this.nodes.size();
        offset.compareAndSet(size, 0);
        int number = offset.getAndIncrement();
        Iterator<V> iterator = nodes.iterator();
        while (number-- > 0) {
            iterator.next();
        }
        return iterator.next();
    }

}
