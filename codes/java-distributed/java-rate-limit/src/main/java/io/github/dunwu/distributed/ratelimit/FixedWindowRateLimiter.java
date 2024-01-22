package io.github.dunwu.distributed.ratelimit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 固定时间窗口限流算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-18
 */
public class FixedWindowRateLimiter implements RateLimiter {

    /**
     * 允许的最大请求数
     */
    private final long maxPermits;

    /**
     * 窗口期时长
     */
    private final long periodMillis;

    /**
     * 窗口期截止时间
     */
    private long lastPeriodMillis;

    /**
     * 请求计数
     */
    private AtomicLong count = new AtomicLong(0);

    public FixedWindowRateLimiter(long qps) {
        this(qps, 1000, TimeUnit.MILLISECONDS);
    }

    public FixedWindowRateLimiter(long maxPermits, long period, TimeUnit timeUnit) {
        this.maxPermits = maxPermits;
        this.periodMillis = timeUnit.toMillis(period);
        this.lastPeriodMillis = System.currentTimeMillis() + this.periodMillis;
    }

    @Override
    public synchronized boolean tryAcquire(int permits) {
        long now = System.currentTimeMillis();
        if (lastPeriodMillis <= now) {
            this.lastPeriodMillis = now + this.periodMillis;
            count = new AtomicLong(0);
        }
        if (count.get() + permits <= maxPermits) {
            count.addAndGet(permits);
            return true;
        } else {
            return false;
        }
    }

}