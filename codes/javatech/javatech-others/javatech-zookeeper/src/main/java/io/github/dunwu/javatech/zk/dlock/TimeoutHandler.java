package io.github.dunwu.javatech.zk.dlock;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-14
 */
public interface TimeoutHandler<V> {

    V onTimeout() throws InterruptedException;

}
