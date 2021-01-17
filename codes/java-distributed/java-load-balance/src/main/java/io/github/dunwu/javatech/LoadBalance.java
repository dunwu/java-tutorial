package io.github.dunwu.javatech;

import java.util.Collection;

/**
 * 负载均衡策略接口
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-21
 */
public interface LoadBalance<N extends Node> {

    void buildNodes(Collection<N> collection);

    void addNode(N node);

    void removeNode(N node);

    N select();

}
