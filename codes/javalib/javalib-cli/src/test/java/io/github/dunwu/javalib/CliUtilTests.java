package io.github.dunwu.javalib;

import org.junit.Test;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019/10/29
 */
public class CliUtilTests {

    @Test
    public void prepare() throws Exception {
        String[] args = { "-sql", "select * from aa", "-name", "测试" };
        CliUtil.prepare(args);
    }

}
