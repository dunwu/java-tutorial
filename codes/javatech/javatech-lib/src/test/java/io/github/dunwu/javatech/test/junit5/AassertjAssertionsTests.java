package io.github.dunwu.javatech.test.junit5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * assertj Assertions 示例
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-07-29
 */
public class AassertjAssertionsTests {

    @Test
    void exceptionTesting() {
        Throwable exception = Assertions.catchThrowable(() -> {
            throw new IllegalArgumentException("a message");
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("a message");
    }

    @Test
    void standardAssertions() {
        Assertions.assertThat(2).isEqualTo(2);
        Assertions.assertThat('a').isLessThan('b');
    }
}
