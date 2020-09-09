package io.github.dunwu.trouble;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class BatchInsertApplication implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public BatchInsertApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchInsertApplication.class, args);
    }

    @Override
    public void run(String... args) {

        long begin = System.nanoTime();
        String sql = "INSERT INTO `testuser` (`name`) VALUES (?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, "user" + i);
            }

            @Override
            public int getBatchSize() {
                return 100000;
            }
        });
        long time = System.nanoTime() - begin;
        log.info("took : {} ms", TimeUnit.NANOSECONDS.toMillis(time));
    }

}
