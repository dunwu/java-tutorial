package io.github.dunwu.javatech;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * (加权)随机负载均衡策略
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://www.cnblogs.com/CodeBear/archive/2019/03/11/10508880.html">Zhang Peng</a>
 * @since 2020-01-20
 */
public class RandomLoadBalance<V extends Node> implements LoadBalance<V> {

    private boolean weightMode;

    private final Random random = ThreadLocalRandom.current();

    private Set<V> nodes = Collections.emptyNavigableSet();

    public RandomLoadBalance() {
        this.weightMode = false;
    }

    public RandomLoadBalance(boolean weightMode) {
        this.weightMode = weightMode;
    }

    @Override
    public void buildInList(final Collection<V> collection) {
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

        List<V> list = new ArrayList<>();
        for (V node : nodes) {
            for (int i = 0; i < node.getWeight(); i++) {
                list.add(node);
            }
        }

        int totalWeight = nodes.stream().mapToInt(Node::getWeight).sum();
        int number = random.nextInt(totalWeight);
        return list.get(number);
    }

    private V getNextInNormalMode() {
        if (CollectionUtil.isEmpty(nodes)) {
            return null;
        }

        int number = random.nextInt(nodes.size());
        Iterator<V> iterator = nodes.iterator();
        while (number-- > 0) {
            iterator.next();
        }
        return iterator.next();
    }

}
