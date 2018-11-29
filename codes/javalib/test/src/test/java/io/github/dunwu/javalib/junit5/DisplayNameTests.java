package io.github.dunwu.javalib.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Junit5 定制测试类和方法的显示名称
 * @author Zhang Peng
 * @date 2018-11-29
 */
@DisplayName("A special test case")
class DisplayNameTests {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() { }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() { }

    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() { }
}
