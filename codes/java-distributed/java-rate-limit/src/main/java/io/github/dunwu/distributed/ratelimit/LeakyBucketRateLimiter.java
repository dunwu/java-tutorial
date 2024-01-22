package io.github.dunwu.distributed.ratelimit;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 漏桶限流算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-18
 */
public class LeakyBucketRateLimiter implements RateLimiter {

    /**
     * QPS
     */
    private final int qps;

    /**
     * 桶的容量
     */
    private final long capacity;

    /**
     * 计算的起始时间
     */
    private long beginTimeMillis;

    /**
     * 桶中当前的水量
     */
    private final AtomicLong waterNum = new AtomicLong(0);

    public LeakyBucketRateLimiter(int qps, int capacity) {
        this.qps = qps;
        this.capacity = capacity;
    }

    @Override
    public synchronized boolean tryAcquire(int permits) {

        // 如果桶中没有水，直接放行
        if (waterNum.get() == 0) {
            beginTimeMillis = System.currentTimeMillis();
            waterNum.addAndGet(permits);
            return true;
        }

        // 计算水量
        long leakedWaterNum = ((System.currentTimeMillis() - beginTimeMillis) / 1000) * qps;
        long currentWaterNum = waterNum.get() - leakedWaterNum;
        waterNum.set(Math.max(0, currentWaterNum));

        // 重置时间
        beginTimeMillis = System.currentTimeMillis();

        if (waterNum.get() + permits < capacity) {
            waterNum.addAndGet(permits);
            return true;
        } else {
            return false;
        }
    }

}