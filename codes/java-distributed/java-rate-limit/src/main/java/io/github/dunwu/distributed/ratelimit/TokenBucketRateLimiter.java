package io.github.dunwu.distributed.ratelimit;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 令牌桶限流算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-18
 */
public class TokenBucketRateLimiter implements RateLimiter {

    /**
     * QPS
     */
    private final long qps;

    /**
     * 桶的容量
     */
    private final long capacity;

    /**
     * 上一次令牌发放时间
     */
    private long endTimeMillis;

    /**
     * 桶中当前的令牌数量
     */
    private final AtomicLong tokenNum = new AtomicLong(0);

    public TokenBucketRateLimiter(long qps, long capacity) {
        this.qps = qps;
        this.capacity = capacity;
        this.endTimeMillis = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire(int permits) {

        long now = System.currentTimeMillis();
        long gap = now - endTimeMillis;

        // 计算令牌数
        long newTokenNum = (gap * qps / 1000);
        long currentTokenNum = tokenNum.get() + newTokenNum;
        tokenNum.set(Math.min(capacity, currentTokenNum));

        if (tokenNum.get() < permits) {
            return false;
        } else {
            tokenNum.addAndGet(-permits);
            endTimeMillis = now;
            return true;
        }
    }

}