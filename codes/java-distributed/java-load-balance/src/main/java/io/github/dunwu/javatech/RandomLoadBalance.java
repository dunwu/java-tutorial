package io.github.dunwu.javatech;

import java.util.List;
import java.util.Random;

/**
 * (加权)随机负载均衡策略
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://www.cnblogs.com/CodeBear/archive/2019/03/11/10508880.html">Zhang Peng</a>
 * @since 2020-01-20
 */
public class RandomLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    private final Random random = new Random();

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        int index = random.nextInt(nodes.size());
        return nodes.get(index);
    }

}
