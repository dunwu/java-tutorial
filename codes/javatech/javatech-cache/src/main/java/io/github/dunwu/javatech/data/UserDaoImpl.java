package io.github.dunwu.javatech.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImpl implements UserDao {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<User> users) {
        String sql = "INSERT INTO user(name, age, address, email) VALUES(?, ?, ?, ?)";

        List<Object[]> params = new ArrayList<>();

        users.forEach(item -> {
            params.add(new Object[] { item.getName(), item.getAge(), item.getAddress(), item.getEmail() });
        });
        jdbcTemplate.batchUpdate(sql, params);
    }

    @Override
    public Integer count() {
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user", Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteByName(String name) {
        int result = jdbcTemplate.update("DELETE FROM user WHERE name = ?", name);
        log.info("[Delete] name = {}", name);
        return result;
    }

    @Override
    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO user(name, age, address, email) VALUES(?, ?, ?, ?)", user.getName(),
            user.getAge(), user.getAddress(), user.getEmail());
    }

    @Override
    public List<User> list() {
        return jdbcTemplate.query("select * from USER", new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User queryByName(String name) {

        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM user WHERE name = ?",
                new BeanPropertyRowMapper<>(User.class), name);
            log.info("[Query] name = {}, result = {}", name, user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recreateTable() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS user");

        String sqlStatement =
            "CREATE TABLE user (\n" + "    id      BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',\n"
                + "    name    VARCHAR(10)         NOT NULL DEFAULT '' COMMENT '用户名',\n"
                + "    age     TINYINT(3)          NOT NULL DEFAULT 0 COMMENT '年龄',\n"
                + "    address VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '地址',\n"
                + "    email   VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '邮件',\n" + "    PRIMARY KEY (id)\n"
                + ") COMMENT = '用户表';";
        jdbcTemplate.execute(sqlStatement);
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update("UPDATE USER SET name=?, age=?, address=?, email=? WHERE id=?", user.getName(),
            user.getAge(), user.getAddress(), user.getEmail(), user.getId());
        return user;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
