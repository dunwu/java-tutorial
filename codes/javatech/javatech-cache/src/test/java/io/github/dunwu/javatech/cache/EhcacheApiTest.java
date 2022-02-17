package io.github.dunwu.javatech.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Ehcache API 测试（没有和任何框架集成的情况）
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-09-04
 */
public class EhcacheApiTest {

    /**
     * 使用Ehcache默认配置(classpath下的ehcache.xml)获取单例的CacheManager实例
     */
    @Test
    public void operation() {
        CacheManager manager = CacheManager.newInstance("src/test/resources/ehcache/ehcache.xml");

        // 获得Cache的引用
        Cache cache = manager.getCache("users");

        // 将一个Element添加到Cache
        cache.put(new Element("key1", "value1"));

        // 获取Element，Element类支持序列化，所以下面两种方法都可以用
        Element element1 = cache.get("key1");
        // 获取非序列化的值
        System.out.println("key=" + element1.getObjectKey() + ", value=" + element1.getObjectValue());
        // 获取序列化的值
        System.out.println("key=" + element1.getKey() + ", value=" + element1.getValue());

        // 更新Cache中的Element
        cache.put(new Element("key1", "value2"));
        Element element2 = cache.get("key1");
        System.out.println("key=" + element2.getObjectKey() + ", value=" + element2.getObjectValue());

        // 获取Cache的元素数
        System.out.println("cache size:" + cache.getSize());

        // 获取MemoryStore的元素数
        System.out.println("MemoryStoreSize:" + cache.getMemoryStoreSize());

        // 获取DiskStore的元素数
        System.out.println("DiskStoreSize:" + cache.getDiskStoreSize());

        // 移除Element
        cache.remove("key1");
        System.out.println("cache size:" + cache.getSize());

        // 关闭当前CacheManager对象
        manager.shutdown();

        // 关闭CacheManager单例实例
        CacheManager.getInstance().shutdown();
    }

    /**
     * 使用Ehcache默认配置(classpath下的ehcache.xml)获取单例的CacheManager实例
     */
    @Test
    public void create01() {
        CacheManager cacheManager = CacheManager.create();

        String[] cacheNames = cacheManager.getCacheNames();
        for (String name : cacheNames) {
            System.out.println("name:" + name);
        }

        cacheManager.shutdown();
    }

    /**
     * 使用Ehcache默认配置(classpath下的ehcache.xml)新建一个单例的CacheManager实例
     */
    @Test
    public void create02() {
        CacheManager.newInstance();

        String[] cacheNames = CacheManager.getInstance().getCacheNames();
        for (String name : cacheNames) {
            System.out.println("name:" + name);
        }

        // 关闭CacheManager单例实例
        CacheManager.getInstance().shutdown();
    }

    /**
     * 使用不同的配置文件分别创建一个CacheManager实例
     */
    @Test
    public void create03() {
        CacheManager manager1 = CacheManager.newInstance("src/test/resources/ehcache/ehcache1.xml");
        CacheManager manager2 = CacheManager.newInstance("src/test/resources/ehcache/ehcache1.xml");
        String[] cacheNamesForManager1 = manager1.getCacheNames();
        String[] cacheNamesForManager2 = manager2.getCacheNames();

        for (String name : cacheNamesForManager1) {
            System.out.println("[ehcache1.xml]name:" + name);
        }

        for (String name : cacheNamesForManager2) {
            System.out.println("[ehcache2.xml]name:" + name);
        }

        manager1.shutdown();
        manager2.shutdown();
    }

    /**
     * 基于classpath下的配置文件创建CacheManager实例
     */
    @Test
    public void create04() {
        URL url = getClass().getResource("/ehcache/ehcache.xml");
        CacheManager manager = CacheManager.newInstance(url);
        String[] cacheNames = manager.getCacheNames();

        for (String name : cacheNames) {
            System.out.println("[ehcache.xml]name:" + name);
        }

        manager.shutdown();
    }

    /**
     * 基于IO流得到配置文件，并创建CacheManager实例
     */
    @Test
    public void create05() throws Exception {
        InputStream fis = new FileInputStream(new File("src/test/resources/ehcache/ehcache.xml").getAbsolutePath());
        CacheManager manager = CacheManager.newInstance(fis);
        fis.close();
        String[] cacheNames = manager.getCacheNames();

        for (String name : cacheNames) {
            System.out.println("[ehcache.xml]name:" + name);
        }

        manager.shutdown();
    }

    /**
     * 使用默认配置(classpath下的ehcache.xml)添加缓存
     */
    @Test
    public void addAndRemove01() {
        CacheManager singletonManager = CacheManager.create();

        // 添加缓存
        singletonManager.addCache("testCache");

        // 打印配置信息和状态
        Cache test = singletonManager.getCache("testCache");
        System.out.println("cache name:" + test.getCacheConfiguration().getName());
        System.out.println("cache status:" + test.getStatus().toString());
        System.out.println("maxElementsInMemory:" + test.getCacheConfiguration().getMaxElementsInMemory());
        System.out.println("timeToIdleSeconds:" + test.getCacheConfiguration().getTimeToIdleSeconds());
        System.out.println("timeToLiveSeconds:" + test.getCacheConfiguration().getTimeToLiveSeconds());

        // 删除缓存
        singletonManager.removeCache("testCache");
        System.out.println("cache status:" + test.getStatus().toString());

        singletonManager.shutdown();
    }

    /**
     * 使用自定义配置添加缓存，注意缓存未添加进CacheManager之前并不可用
     */
    @Test
    public void addAndRemove02() {
        CacheManager singletonManager = CacheManager.create();

        // 添加缓存
        Cache memoryOnlyCache = new Cache("testCache2", 5000, false, false, 5, 2);
        singletonManager.addCache(memoryOnlyCache);

        // 打印配置信息和状态
        Cache test = singletonManager.getCache("testCache2");
        System.out.println("cache name:" + test.getCacheConfiguration().getName());
        System.out.println("cache status:" + test.getStatus().toString());
        System.out.println("maxElementsInMemory:" + test.getCacheConfiguration().getMaxElementsInMemory());
        System.out.println("timeToIdleSeconds:" + test.getCacheConfiguration().getTimeToIdleSeconds());
        System.out.println("timeToLiveSeconds:" + test.getCacheConfiguration().getTimeToLiveSeconds());

        // 删除缓存
        singletonManager.removeCache("testCache2");
        System.out.println("cache status:" + test.getStatus().toString());

        singletonManager.shutdown();
    }

    /**
     * 使用特定的配置添加缓存
     */
    @Test
    public void addAndRemove03() {
        CacheManager manager = CacheManager.create();

        // 添加缓存
        Cache testCache = new Cache(new CacheConfiguration("testCache3", 5000)
            .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU).eternal(false).timeToLiveSeconds(60)
            .timeToIdleSeconds(30).diskExpiryThreadIntervalSeconds(0)
            .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP)));
        manager.addCache(testCache);

        // 打印配置信息和状态
        Cache test = manager.getCache("testCache3");
        System.out.println("cache name:" + test.getCacheConfiguration().getName());
        System.out.println("cache status:" + test.getStatus().toString());
        System.out.println("maxElementsInMemory:" + test.getCacheConfiguration().getMaxElementsInMemory());
        System.out.println("timeToIdleSeconds:" + test.getCacheConfiguration().getTimeToIdleSeconds());
        System.out.println("timeToLiveSeconds:" + test.getCacheConfiguration().getTimeToLiveSeconds());

        // 删除缓存
        manager.removeCache("testCache3");
        System.out.println("cache status:" + test.getStatus().toString());

        manager.shutdown();
    }

}
