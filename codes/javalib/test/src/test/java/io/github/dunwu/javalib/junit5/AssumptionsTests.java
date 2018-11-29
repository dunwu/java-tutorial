package io.github.dunwu.javalib.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * Junit5 断言示例
 * @author Zhang Peng
 * @date 2018-11-29
 */
class AssumptionsTests {

    @Test
    void testOnlyOnCiServer() {
        assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")), () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")), () -> {
            // perform these assertions only on the CI server
            assertEquals(2, 2);
        });

        // perform these assertions in all environments
        assertEquals("a string", "a string");
    }

}
