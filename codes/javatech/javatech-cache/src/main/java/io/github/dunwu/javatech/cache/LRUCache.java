package io.github.dunwu.javatech.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通过继承 LinkedHashMap 来实现一个简单的 LRUHashMap
 *
 * 核心思想就是：LRU （最近最少使用）算法
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-01-18
 */
class LRUCache extends LinkedHashMap {

    private final int max;
    private Object lock;

    public LRUCache(int max) {
        //无需扩容
        super((int) (max * 1.4f), 0.75f, true);
        this.max = max;
        this.lock = new Object();
    }

    /**
     * 重写LinkedHashMap的removeEldestEntry方法即可 在Put的时候判断，如果为true，就会删除最老的
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > max;
    }

    public Object getValue(Object key) {
        synchronized (lock) {
            return get(key);
        }
    }

    public void putValue(Object key, Object value) {
        synchronized (lock) {
            put(key, value);
        }
    }

    public boolean removeValue(Object key) {
        synchronized (lock) {
            return remove(key) != null;
        }
    }

    public boolean removeAll() {
        clear();
        return true;
    }

}
