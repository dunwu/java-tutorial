package io.github.dunwu.javatech.data;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserDao {

    void batchInsert(List<User> users);

    Integer count();

    @CacheEvict(value = "dunwu:users", key = "#name")
    int deleteByName(String name);

    void insert(User user);

    List<User> list();

    @Cacheable(value = "dunwu:users", key = "#name")
    User queryByName(String name);

    void recreateTable();

    @CachePut(value = "dunwu:users", key = "#user.name")
    User update(User user);

    JdbcTemplate getJdbcTemplate();

}
