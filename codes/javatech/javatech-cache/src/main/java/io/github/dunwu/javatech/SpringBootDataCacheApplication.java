package io.github.dunwu.javatech;

import io.github.dunwu.javatech.data.User;
import io.github.dunwu.javatech.data.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-10-14
 */
@EnableCaching
@SpringBootApplication
public class SpringBootDataCacheApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;

    public SpringBootDataCacheApplication(UserDao userDao) {
        this.userDao = userDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataCacheApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (userDao != null) {
            printDataSourceInfo(userDao.getJdbcTemplate());
            log.info("连接数据源成功！");
        } else {
            log.error("连接数据源失败！");
            return;
        }

        for (int i = 1; i <= 3; i++) {
            User user = userDao.queryByName("张三");
            log.info("第 {} 次查询 name = {}", i, user.toString());
        }

        for (int i = 1; i <= 3; i++) {
            User user = userDao.queryByName("李四");
            log.info("第 {} 次查询 name = {}", i, user.toString());
        }

        User result = userDao.queryByName("张三");
        result.setAddress("深圳");
        userDao.update(result);

        for (int i = 1; i <= 3; i++) {
            User user = userDao.queryByName("张三");
            log.info("第 {} 次查询 name = {}", i, user.toString());
        }
    }

    public void printDataSourceInfo(JdbcTemplate jdbcTemplate) throws SQLException {

        DataSource dataSource = jdbcTemplate.getDataSource();

        Connection connection;
        if (dataSource != null) {
            connection = dataSource.getConnection();
        } else {
            log.error("获取 DataSource 失败");
            return;
        }

        if (connection != null) {
            log.info("DB URL: {}", connection.getMetaData().getURL());
        } else {
            log.error("获取 Connection 失败");
        }
    }

}
