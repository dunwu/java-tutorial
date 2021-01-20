package io.github.dunwu.javatech;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author peng.zhang
 * @date 2021/1/19
 */
public class WeightRoundRobinLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    /**
     * 60秒
     */
    private static final int RECYCLE_PERIOD = 60000;

    /**
     * Node hashcode 到 WeightedRoundRobin 的映射关系
     */
    private ConcurrentMap<Integer, WeightedRoundRobin> weightMap = new ConcurrentHashMap<>();

    /**
     * 原子更新锁
     */
    private AtomicBoolean updateLock = new AtomicBoolean();

    @Override
    protected N doSelect(List<N> nodes, String ip) {

        int totalWeight = 0;
        long maxCurrent = Long.MIN_VALUE;

        // 获取当前时间
        long now = System.currentTimeMillis();
        N selectedNode = null;
        WeightedRoundRobin selectedWRR = null;

        // 下面这个循环主要做了这样几件事情：
        //   1. 遍历 Node 列表，检测当前 Node 是否有相应的 WeightedRoundRobin，没有则创建
        //   2. 检测 Node 权重是否发生了变化，若变化了，则更新 WeightedRoundRobin 的 weight 字段
        //   3. 让 current 字段加上自身权重，等价于 current += weight
        //   4. 设置 lastUpdate 字段，即 lastUpdate = now
        //   5. 寻找具有最大 current 的 Node，以及 Node 对应的 WeightedRoundRobin，
        //      暂存起来，留作后用
        //   6. 计算权重总和
        for (N node : nodes) {
            int hashCode = node.hashCode();
            WeightedRoundRobin weightedRoundRobin = weightMap.get(hashCode);
            int weight = node.getWeight();
            if (weight < 0) {
                weight = 0;
            }

            // 检测当前 Node 是否有对应的 WeightedRoundRobin，没有则创建
            if (weightedRoundRobin == null) {
                weightedRoundRobin = new WeightedRoundRobin();
                // 设置 Node 权重
                weightedRoundRobin.setWeight(weight);
                // 存储 url 唯一标识 identifyString 到 weightedRoundRobin 的映射关系
                weightMap.putIfAbsent(hashCode, weightedRoundRobin);
                weightedRoundRobin = weightMap.get(hashCode);
            }
            // Node 权重不等于 WeightedRoundRobin 中保存的权重，说明权重变化了，此时进行更新
            if (weight != weightedRoundRobin.getWeight()) {
                weightedRoundRobin.setWeight(weight);
            }

            // 让 current 加上自身权重，等价于 current += weight
            long current = weightedRoundRobin.increaseCurrent();
            // 设置 lastUpdate，表示近期更新过
            weightedRoundRobin.setLastUpdate(now);
            // 找出最大的 current
            if (current > maxCurrent) {
                maxCurrent = current;
                // 将具有最大 current 权重的 Node 赋值给 selectedNode
                selectedNode = node;
                // 将 Node 对应的 weightedRoundRobin 赋值给 selectedWRR，留作后用
                selectedWRR = weightedRoundRobin;
            }

            // 计算权重总和
            totalWeight += weight;
        }

        // 对 weightMap 进行检查，过滤掉长时间未被更新的节点。
        // 该节点可能挂了，nodes 中不包含该节点，所以该节点的 lastUpdate 长时间无法被更新。
        // 若未更新时长超过阈值后，就会被移除掉，默认阈值为60秒。
        if (!updateLock.get() && nodes.size() != weightMap.size()) {
            if (updateLock.compareAndSet(false, true)) {
                try {
                    // 遍历修改，即移除过期记录
                    weightMap.entrySet().removeIf(item -> now - item.getValue().getLastUpdate() > RECYCLE_PERIOD);
                } finally {
                    updateLock.set(false);
                }
            }
        }

        if (selectedNode != null) {
            // 让 current 减去权重总和，等价于 current -= totalWeight
            selectedWRR.decreaseCurrent(totalWeight);
            // 返回具有最大 current 的 Node
            return selectedNode;
        }

        // should not happen here
        return nodes.get(0);
    }

    protected static class WeightedRoundRobin {

        // 服务提供者权重
        private int weight;
        // 当前权重
        private AtomicLong current = new AtomicLong(0);
        // 最后一次更新时间
        private long lastUpdate;

        public long increaseCurrent() {
            // current = current + weight；
            return current.addAndGet(weight);
        }

        public long decreaseCurrent(int total) {
            // current = current - total;
            return current.addAndGet(-1 * total);
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
            // 初始情况下，current = 0
            current.set(0);
        }

        public AtomicLong getCurrent() {
            return current;
        }

        public void setCurrent(AtomicLong current) {
            this.current = current;
        }

        public long getLastUpdate() {
            return lastUpdate;
        }

        public void setLastUpdate(long lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

    }

}
