package io.github.dunwu.javatech;

import java.util.Collection;

/**
 * 负载均衡策略接口
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-21
 */
public interface LoadBalance<V extends Node> {

    void buildInList(Collection<V> collection);

    void addNode(V node);

    void removeNode(V node);

    V next();

}
