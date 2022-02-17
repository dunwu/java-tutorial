package io.github.dunwu.javatech.cache.spring;

import io.github.dunwu.javatech.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * Spring 缓存接口测试类
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-09-04
 */
@Component
public class SpringCacheDemo {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 测试当前真实工作的 CacheManager 是什么
     */
    public void getCacheManager() {
        System.out.println("当前 CacheManager 类：" + cacheManager.getClass());
        System.out.println(cacheManager.getCacheNames());
    }

    /**
     * 测试@Cacheable
     */
    public void testFindUser() throws InterruptedException {
        // 设置查询条件
        User user1 = new User(1L, null);
        User user2 = new User(2L, null);
        User user3 = new User(3L, null);
        User user4 = new User(3L, null);

        System.out.println("第一次查询");
        System.out.println(userService.findUser(user1));
        System.out.println(userService.findUser(user2));
        System.out.println(userService.findUser(user3));
        System.out.println(userService.findUser(user4));

        // 如果缓存有效，应该不会打印 查找数据库 id = %d 成功 这样的信息
        System.out.println("\n第二次查询");
        System.out.println(userService.findUser(user1));
        System.out.println(userService.findUser(user2));
        System.out.println(userService.findUser(user3));
        System.out.println(userService.findUser(user4));

        // 在classpath:ehcache/ehcache.xml中，设置了userCache的缓存时间为3000 ms, 这里设置等待
        Thread.sleep(3000);

        System.out.println("\n缓存过期，再次查询");
        System.out.println(userService.findUser(user1));
        System.out.println(userService.findUser(user2));
        System.out.println(userService.findUser(user3));
    }

    /**
     * 测试@Cacheable设置Spring SpEL条件限制
     */
    public void testFindUserInLimit() throws InterruptedException {
        // 设置查询条件
        User user1 = new User(1L, null);
        User user2 = new User(2L, null);
        User user3 = new User(3L, null);

        System.out.println("第一次查询user info");
        System.out.println(userService.findUserInLimit(user1));
        System.out.println(userService.findUserInLimit(user2));
        System.out.println(userService.findUserInLimit(user3));

        System.out.println("\n第二次查询user info");
        System.out.println(userService.findUserInLimit(user1));
        System.out.println(userService.findUserInLimit(user2));
        System.out.println(userService.findUserInLimit(user3)); // 超过限制条件，不会从缓存中读数据

        // 在classpath:ehcache/ehcache.xml中，设置了userCache的缓存时间为3000 ms, 这里设置等待
        Thread.sleep(3000);

        System.out.println("\n缓存过期，再次查询");
        System.out.println(userService.findUserInLimit(user1));
        System.out.println(userService.findUserInLimit(user2));
        System.out.println(userService.findUserInLimit(user3));
    }

    /**
     * 测试@CachePut
     */
    public void testUpdateUser() {
        // 设置查询条件
        User user1 = new User(2L, null);
        User user2 = new User(2L, null);

        System.out.println(userService.findUser(user1));
        System.out.println(userService.findUser(user2));
        userService.updateUser(new User(2L, "尼古拉斯.赵四"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(userService.findUser(user1));
        System.out.println(userService.findUser(user2));
    }

    /**
     * 测试@CacheEvict删除指定缓存
     */
    public void testRemoveUser() {
        // 设置查询条件
        User user1 = new User(1L, null);

        System.out.println("数据删除前：");
        System.out.println(userService.findUser(user1));

        userService.removeUser(user1);
        System.out.println("数据删除后：");
        System.out.println(userService.findUser(user1));
    }

    /**
     * 测试@CacheEvict删除所有缓存
     */
    public void testClear() {
        System.out.println("数据清空前：");
        System.out.println(userService.findUser(new User(1L, null)));
        System.out.println(userService.findUser(new User(2L, null)));
        System.out.println(userService.findUser(new User(3L, null)));

        userService.clear();
        System.out.println("\n数据清空后：");
        System.out.println(userService.findUser(new User(1L, null)));
        System.out.println(userService.findUser(new User(2L, null)));
        System.out.println(userService.findUser(new User(3L, null)));
    }

}
