package io.github.dunwu.distributed.ratelimit;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 滑动时间窗口限流算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-18
 */
public class SlidingWindowRateLimiter implements RateLimiter {

    /**
     * 允许的最大请求数
     */
    private final long maxPermits;

    /**
     * 窗口期时长
     */
    private final long periodMillis;

    /**
     * 分片窗口期时长
     */
    private final long shardPeriodMillis;

    /**
     * 窗口期截止时间
     */
    private long lastPeriodMillis;

    /**
     * 分片窗口数
     */
    private final int shardNum;

    /**
     * 请求总计数
     */
    private final AtomicLong totalCount = new AtomicLong(0);

    /**
     * 分片窗口计数列表
     */
    private final List<AtomicLong> countList = new LinkedList<>();

    public SlidingWindowRateLimiter(long qps, int shardNum) {
        this(qps, 1000, TimeUnit.MILLISECONDS, shardNum);
    }

    public SlidingWindowRateLimiter(long maxPermits, long period, TimeUnit timeUnit, int shardNum) {
        this.maxPermits = maxPermits;
        this.periodMillis = timeUnit.toMillis(period);
        this.lastPeriodMillis = System.currentTimeMillis();
        this.shardPeriodMillis = timeUnit.toMillis(period) / shardNum;
        this.shardNum = shardNum;
        for (int i = 0; i < shardNum; i++) {
            countList.add(new AtomicLong(0));
        }
    }

    @Override
    public synchronized boolean tryAcquire(int permits) {
        long now = System.currentTimeMillis();
        if (now > lastPeriodMillis) {
            for (int shardId = 0; shardId < shardNum; shardId++) {
                long shardCount = countList.get(shardId).get();
                totalCount.addAndGet(-shardCount);
                countList.set(shardId, new AtomicLong(0));
                lastPeriodMillis += shardPeriodMillis;
            }
        }
        int shardId = (int) (now % periodMillis / shardPeriodMillis);
        if (totalCount.get() + permits <= maxPermits) {
            countList.get(shardId).addAndGet(permits);
            totalCount.addAndGet(permits);
            return true;
        } else {
            return false;
        }
    }

}