package io.github.dunwu.distributed.ratelimit;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Redis + Lua 实现的令牌桶限流算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2024-01-23
 */
public class RedisTokenBucketRateLimiter implements RateLimiter {

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

        SCRIPT = FileUtil.readString(ResourceUtil.getResource("scripts/token_bucket_rate_limit.lua"),
            StandardCharsets.UTF_8);
    }

    private final long qps;
    private final long capacity;
    private final String tokenKey;
    private final String timeKey;

    public RedisTokenBucketRateLimiter(long qps, long capacity, String tokenKey, String timeKey) {
        this.qps = qps;
        this.capacity = capacity;
        this.tokenKey = tokenKey;
        this.timeKey = timeKey;
    }

    @Override
    public boolean tryAcquire(int permits) {
        long now = System.currentTimeMillis();
        List<String> keys = CollectionUtil.newLinkedList(tokenKey, timeKey);
        List<String> args = CollectionUtil.newLinkedList(String.valueOf(permits), String.valueOf(qps),
            String.valueOf(capacity), String.valueOf(now));
        Object eval = JEDIS.eval(SCRIPT, keys, args);
        long value = (long) eval;
        return value != -1;
    }

    public static void main(String[] args) throws InterruptedException {

        int qps = 20;
        int bucket = 100;
        RedisTokenBucketRateLimiter redisTokenBucketRateLimiter =
            new RedisTokenBucketRateLimiter(qps, bucket, "token:rate:limit", "token:rate:limit:time");

        // 先将令牌桶预热令牌申请完，后续才能真实反映限流 QPS
        redisTokenBucketRateLimiter.tryAcquire(bucket);
        TimeUnit.SECONDS.sleep(1);

        // 模拟在一分钟内，不断收到请求，限流是否有效
        int seconds = 60;
        long okNum = 0L;
        long total = 0L;
        long beginTime = System.currentTimeMillis();
        for (int second = 0; second < seconds; second++) {
            int num = RandomUtil.randomInt(qps, 100);
            for (int i = 0; i < num; i++) {
                total++;
                if (redisTokenBucketRateLimiter.tryAcquire(1)) {
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
