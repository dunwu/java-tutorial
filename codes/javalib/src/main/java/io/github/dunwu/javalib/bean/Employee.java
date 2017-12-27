package io.github.dunwu.javalib.bean;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Lombok 示例
 * @see http://jnb.ociweb.com/jnb/jnbJan2010.html
 * @author Zhang Peng
 */
public class Employee {
    @Getter
    @Setter
    private boolean employed = true;

    @Setter(AccessLevel.PROTECTED)
    private String name;
}
