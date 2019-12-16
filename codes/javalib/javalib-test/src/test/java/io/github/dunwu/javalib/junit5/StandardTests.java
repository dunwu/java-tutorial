package io.github.dunwu.javalib.junit5;

import org.junit.jupiter.api.*;

/**
 * Junit5 标准测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2018-11-29
 */
@Disabled
class StandardTests {

    @AfterAll
    static void afterAll() {
        System.out.println("call afterAll()");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("call beforeAll()");
    }

    @AfterEach
    void afterEach() {
        System.out.println("call afterEach()");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("call beforeEach()");
    }

    @Test
    void failingTest() {
        System.out.println("call failingTest()");
        // fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        System.out.println("call skippedTest()");
        // not executed
    }

    @Test
    void succeedingTest() {
        System.out.println("call succeedingTest()");
    }

}
