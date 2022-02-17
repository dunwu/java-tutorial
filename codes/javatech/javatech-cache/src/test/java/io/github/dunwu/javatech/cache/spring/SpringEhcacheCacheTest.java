package io.github.dunwu.javatech.cache.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用 Ehcache 作为 Spring 缓存测试
 * <p>
 * 配置内容见：spring/spring-ehcache.xml
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-09-04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ehcache.xml" })
public class SpringEhcacheCacheTest {

    @Autowired
    private SpringCacheDemo springCacheDemo;

    /**
     * 测试当前真实工作的 CacheManager 是什么
     */
    @Test
    public void getCacheManager() {
        springCacheDemo.getCacheManager();
    }

    /**
     * 测试@Cacheable
     */
    @Test
    public void testFindUser() throws InterruptedException {
        springCacheDemo.testFindUser();
    }

    /**
     * 测试@Cacheable设置Spring SpEL条件限制
     */
    @Test
    public void testFindUserInLimit() throws InterruptedException {
        springCacheDemo.testFindUserInLimit();
    }

    /**
     * 测试@CachePut
     */
    @Test
    public void testUpdateUser() {
        springCacheDemo.testUpdateUser();
    }

    /**
     * 测试@CacheEvict删除指定缓存
     */
    @Test
    public void testRemoveUser() {
        springCacheDemo.testRemoveUser();
    }

    /**
     * 测试@CacheEvict删除所有缓存
     */
    @Test
    public void testClear() {
        springCacheDemo.testClear();
    }

}
