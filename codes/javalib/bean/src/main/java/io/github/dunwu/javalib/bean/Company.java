package io.github.dunwu.javalib.bean;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

/**
 * Lombok 示例
 * @see http://jnb.ociweb.com/jnb/jnbJan2010.html
 * @author Zhang Peng
 */
@Data(staticConstructor = "of")
public class Company {
    private final Person founder;
    private String name;
    protected List<Person> employees;
}
