package io.github.dunwu.distributed;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2021-01-18
 */
public abstract class BaseLoadBalance<N extends Node> implements LoadBalance<N> {

    @Override
    public N select(List<N> nodes, String ip) {
        // nodes 列表为空，返回 null
        if (CollectionUtil.isEmpty(nodes)) {
            return null;
        }

        // 如果 nodes 列表中仅有一个 node，直接返回即可
        if (nodes.size() == 1) {
            return nodes.get(0);
        }

        return doSelect(nodes, ip);
    }

    /**
     * 负载均衡算法抽象方法，各个算法需要自行实现
     *
     * @param nodes 节点列表
     * @param ip    请求方 IP
     * @return <N> 被选中的节点
     */
    protected abstract N doSelect(List<N> nodes, String ip);

}
