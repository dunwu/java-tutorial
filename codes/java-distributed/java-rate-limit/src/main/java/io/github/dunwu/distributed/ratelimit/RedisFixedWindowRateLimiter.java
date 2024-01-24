package io.github.dunwu.distributed.ratelimit;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redis + Lua 实现的固定时间窗口限流算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-23
 */
public class RedisFixedWindowRateLimiter implements RateLimiter {

    private static final String REDIS_HOST = "localhost";

    private static final int REDIS_PORT = 6379;

    private static final Jedis JEDIS;

    public static final String SCRIPT;

    static {
        // Jedis 有多种构造方法，这里选用最简单的一种情况
        JEDIS = new Jedis(REDIS_HOST, REDIS_PORT);

        // 触发 ping 命令
        try {
            JEDIS.ping();
            System.out.println("jedis 连接成功");
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        }

        SCRIPT = FileUtil.readString(ResourceUtil.getResource("scripts/fixed_window_rate_limit.lua"),
            StandardCharsets.UTF_8);
    }

    private final long maxPermits;
    private final long periodSeconds;
    private final String key;

    public RedisFixedWindowRateLimiter(long qps, String key) {
        this(qps * 60, 60, TimeUnit.SECONDS, key);
    }

    public RedisFixedWindowRateLimiter(long maxPermits, long period, TimeUnit timeUnit, String key) {
        this.maxPermits = maxPermits;
        this.periodSeconds = timeUnit.toSeconds(period);
        this.key = key;
    }

    @Override
    public boolean tryAcquire(int permits) {
        List<String> keys = Collections.singletonList(key);
        List<String> args = CollectionUtil.newLinkedList(String.valueOf(permits), String.valueOf(periodSeconds),
            String.valueOf(maxPermits));
        Object eval = JEDIS.eval(SCRIPT, keys, args);
        long value = (long) eval;
        return value != -1;
    }

    public static void main(String[] args) throws InterruptedException {

        int qps = 20;
        RateLimiter jedisFixedWindowRateLimiter = new RedisFixedWindowRateLimiter(qps, "rate:limit:20240122210000");

        // 模拟在一分钟内，不断收到请求，限流是否有效
        int seconds = 60;
        long okNum = 0L;
        long total = 0L;
        long beginTime = System.currentTimeMillis();
        int num = RandomUtil.randomInt(qps, 100);
        for (int second = 0; second < seconds; second++) {
            for (int i = 0; i < num; i++) {
                total++;
                if (jedisFixedWindowRateLimiter.tryAcquire(1)) {
                    okNum++;
                    System.out.println("请求成功");
                } else {
                    System.out.println("请求限流");
                }
            }
            TimeUnit.SECONDS.sleep(1);
        }
        long endTime = System.currentTimeMillis();
        long time = (endTime - beginTime) / 1000;
        System.out.println(StrUtil.format("请求通过数：{}，总请求数：{}，实际 QPS：{}", okNum, total, okNum / time));
    }

}
