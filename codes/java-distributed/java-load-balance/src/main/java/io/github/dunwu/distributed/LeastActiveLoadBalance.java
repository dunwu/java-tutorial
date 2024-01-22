package io.github.dunwu.distributed;

import java.util.List;
import java.util.Random;

/**
 * @author peng.zhang
 * @date 2021/1/18
 */
public class LeastActiveLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final Random random = new Random();

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        int length = nodes.size();
        // 最小的活跃数
        int leastActive = -1;
        // 具有相同“最小活跃数”的服务者提供者（以下用 Node 代称）数量
        int leastCount = 0;
        // leastIndexs 用于记录具有相同“最小活跃数”的 Node 在 nodes 列表中的下标信息
        int[] leastIndexs = new int[length];
        int totalWeight = 0;
        // 第一个最小活跃数的 Node 权重值，用于与其他具有相同最小活跃数的 Node 的权重进行对比，
        // 以检测是否“所有具有相同最小活跃数的 Node 的权重”均相等
        int firstWeight = 0;
        boolean sameWeight = true;

        // 遍历 nodes 列表
        for (int i = 0; i < length; i++) {
            N node = nodes.get(i);
            // 发现更小的活跃数，重新开始
            if (leastActive == -1 || node.getActive() < leastActive) {
                // 使用当前活跃数更新最小活跃数 leastActive
                leastActive = node.getActive();
                // 更新 leastCount 为 1
                leastCount = 1;
                // 记录当前下标值到 leastIndexs 中
                leastIndexs[0] = i;
                totalWeight = node.getWeight();
                firstWeight = node.getWeight();
                sameWeight = true;

                // 当前 Node 的活跃数 node.getActive() 与最小活跃数 leastActive 相同
            } else if (node.getActive() == leastActive) {
                // 在 leastIndexs 中记录下当前 Node 在 nodes 集合中的下标
                leastIndexs[leastCount++] = i;
                // 累加权重
                totalWeight += node.getWeight();
                // 检测当前 Node 的权重与 firstWeight 是否相等，
                // 不相等则将 sameWeight 置为 false
                if (sameWeight && i > 0
                    && node.getWeight() != firstWeight) {
                    sameWeight = false;
                }
            }
        }

        // 当只有一个 Node 具有最小活跃数，此时直接返回该 Node 即可
        if (leastCount == 1) {
            return nodes.get(leastIndexs[0]);
        }

        // 有多个 Node 具有相同的最小活跃数，但它们之间的权重不同
        if (!sameWeight && totalWeight > 0) {
            // 随机生成一个 [0, totalWeight) 之间的数字
            int offsetWeight = random.nextInt(totalWeight);
            // 循环让随机数减去具有最小活跃数的 Node 的权重值，
            // 当 offset 小于等于0时，返回相应的 Node
            for (int i = 0; i < leastCount; i++) {
                int leastIndex = leastIndexs[i];
                // 获取权重值，并让随机数减去权重值
                offsetWeight -= nodes.get(leastIndex).getWeight();
                if (offsetWeight <= 0) {
                    return nodes.get(leastIndex);
                }
            }
        }
        // 如果权重相同或权重为0时，随机返回一个 Node
        return nodes.get(leastIndexs[random.nextInt(leastCount)]);
    }

}
