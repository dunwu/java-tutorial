package io.github.dunwu.javatech.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-07-09
 */
public class CaffeineDemo {

    public static void main(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .expireAfterAccess(1, TimeUnit.SECONDS)
            .maximumSize(10)
            .build();
        cache.put("hello", "hello");
    }

}
