package io.github.dunwu.javatech;

import java.util.List;

/**
 * 负载均衡策略接口
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-21
 */
public interface LoadBalance<N extends Node> {

    N select(List<N> nodes, String ip);

}
