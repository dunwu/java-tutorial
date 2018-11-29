package io.github.dunwu.javalib.junit5;

import io.github.dunwu.javalib.bean.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Zhang Peng
 * @date 2018-11-29
 */
public class ParameterizedTests {
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({"0,    1,   1", "1,    2,   3", "49,  51, 100", "1,  100, 101"})
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
            () -> first + " + " + second + " should equal " + expectedResult);
    }
}
