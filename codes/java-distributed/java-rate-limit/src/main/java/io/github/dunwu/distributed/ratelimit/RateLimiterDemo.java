package io.github.dunwu.distributed.ratelimit;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流器示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-18
 */
@Slf4j
public class RateLimiterDemo {

    public static void main(String[] args) {

        // ============================================================================

        int qps = 20;

        System.out.println("======================= 固定时间窗口限流算法 =======================");
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(qps);
        testRateLimit(fixedWindowRateLimiter, qps);

        System.out.println("======================= 滑动时间窗口限流算法 =======================");
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(qps, 10);
        testRateLimit(slidingWindowRateLimiter, qps);

        System.out.println("======================= 漏桶限流算法 =======================");
        LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(qps, 100);
        testRateLimit(leakyBucketRateLimiter, qps);

        System.out.println("======================= 令牌桶限流算法 =======================");
        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(qps, 100);
        testRateLimit(tokenBucketRateLimiter, qps);
    }

    private static void testRateLimit(RateLimiter rateLimiter, int qps) {

        AtomicInteger okNum = new AtomicInteger(0);
        AtomicInteger limitNum = new AtomicInteger(0);
        ExecutorService executorService = ThreadUtil.newFixedExecutor(10, "限流测试", true);
        long beginTime = System.currentTimeMillis();

        int threadNum = 4;
        final CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            executorService.submit(() -> {
                try {
                    batchRequest(rateLimiter, okNum, limitNum, 1000);
                } catch (Exception e) {
                    log.error("发生异常!", e);
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(10, TimeUnit.SECONDS);
            long endTime = System.currentTimeMillis();
            long gap = endTime - beginTime;
            log.info("限流 QPS: {} -> 实际结果：耗时 {} ms，{} 次请求成功，{} 次请求被限流，实际 QPS: {}",
                qps, gap, okNum.get(), limitNum.get(), okNum.get() * 1000 / gap);
            if (okNum.get() == qps) {
                log.info("限流符合预期");
            }
        } catch (Exception e) {
            log.error("发生异常!", e);
        } finally {
            executorService.shutdown();
        }
    }

    private static void batchRequest(RateLimiter rateLimiter, AtomicInteger okNum, AtomicInteger limitNum, int num)
        throws InterruptedException {
        for (int j = 0; j < num; j++) {
            if (rateLimiter.tryAcquire(1)) {
                log.info("请求成功");
                okNum.getAndIncrement();
            } else {
                log.info("请求限流");
                limitNum.getAndIncrement();
            }
            TimeUnit.MILLISECONDS.sleep(RandomUtil.randomInt(0, 10));
        }
    }

}
