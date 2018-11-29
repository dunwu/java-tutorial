package io.github.dunwu.javalib.junit5;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Junit5 标准测试
 * @author Zhang Peng
 * @date 2018-11-29
 */
class StandardTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandardTests.class);

    @BeforeAll
    static void beforeAll() {
        LOGGER.info("call beforeAll()");
    }

    @BeforeEach
    void beforeEach() {
        LOGGER.info("call beforeEach()");
    }

    @Test
    void succeedingTest() {
        LOGGER.info("call succeedingTest()");
    }

    @Test
    void failingTest() {
        LOGGER.info("call failingTest()");
        // fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        LOGGER.info("call skippedTest()");
        // not executed
    }

    @AfterEach
    void afterEach() {
        LOGGER.info("call afterEach()");
    }

    @AfterAll
    static void afterAll() {
        LOGGER.info("call afterAll()");
    }
}
