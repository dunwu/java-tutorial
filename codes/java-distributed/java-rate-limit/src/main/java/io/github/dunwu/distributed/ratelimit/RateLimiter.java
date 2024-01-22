package io.github.dunwu.distributed.ratelimit;

/**
 * 限流器
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-18
 */
public interface RateLimiter {

    boolean tryAcquire(int permits);

}
