package io.github.dunwu.javatech;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * (加权)随机负载均衡策略
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://www.cnblogs.com/CodeBear/archive/2019/03/11/10508880.html">Zhang Peng</a>
 * @since 2020-01-20
 */
public class RandomLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final Random random = ThreadLocalRandom.current();

    @Override
    protected N doSelect() {
        boolean sameWeight = true;
        int length = nodes.size();
        int totalWeight = 0;
        // 下面这个循环有两个作用
        // 第一是计算总权重 totalWeight，
        // 第二是检测每个服务提供者的权重是否相同
        for (int i = 0; i < length; i++) {
            Integer weight = nodes.get(i).getWeight();
            // 累加权重
            totalWeight += weight;
            // 检测当前服务提供者的权重与上一个服务提供者的权重是否相同，
            // 不相同的话，则将 sameWeight 置为 false。
            if (sameWeight && i > 0 && weight.equals(nodes.get(i - 1).getWeight())) {
                sameWeight = false;
            }
        }

        // 下面的 if 分支主要用于获取随机数，并计算随机数落在哪个区间上
        if (totalWeight > 0 && !sameWeight) {
            // 随机获取一个 [0, totalWeight) 区间内的数字
            int offset = random.nextInt(totalWeight);
            // 循环让 offset 数减去服务提供者权重值，当 offset 小于0时，返回相应的 Node。
            // 举例说明一下，我们有 servers = [A, B, C]，weights = [5, 3, 2]，offset = 7。
            // 第一次循环，offset - 5 = 2 > 0，即 offset > 5，
            // 表明其不会落在服务器 A 对应的区间上。
            // 第二次循环，offset - 3 = -1 < 0，即 5 < offset < 8，
            // 表明其会落在服务器 B 对应的区间上
            for (int i = 0; i < length; i++) {
                // 让随机值 offset 减去权重值
                offset -= nodes.get(i).getWeight();
                if (offset < 0) {
                    // 返回相应的 Node
                    return nodes.get(i);
                }
            }
        }

        // 如果所有服务提供者权重值相同，此时直接随机返回一个即可
        return nodes.get(random.nextInt(length));
    }

}
