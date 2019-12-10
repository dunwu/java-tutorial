package io.github.dunwu.javalib.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2018-11-29
 */
@Disabled
class ParameterizedTests {

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({ "0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101" })
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
            () -> first + " + " + second + " should equal " + expectedResult);
    }

    class Calculator {

        public int add(int a, int b) {
            return a + b;
        }

    }

}
