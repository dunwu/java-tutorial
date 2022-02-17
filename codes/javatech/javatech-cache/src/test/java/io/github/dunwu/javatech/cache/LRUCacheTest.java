package io.github.dunwu.javatech.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-18
 */
public class LRUCacheTest {

    @Test
    public void test() {
        LRUCache cache = new LRUCache(2);
        Assertions.assertNull(cache.get(2));
        cache.put(2, "B");
        Assertions.assertNull(cache.get(1));
        cache.put(1, "A");
        cache.put(3, "C");
        Assertions.assertEquals("A", cache.get(1));
        Assertions.assertEquals(null, cache.get(2));
        Assertions.assertEquals("C", cache.get(3));
    }

}
