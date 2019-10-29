package io.github.dunwu.javalib.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Lombok 示例
 *
 * @author Zhang Peng
 * @see http://jnb.ociweb.com/jnb/jnbJan2010.html
 */
@Data
@ToString(exclude = "age")
@EqualsAndHashCode(exclude = {"age", "sex"})
public class Person {

	private String name;

	private Integer age;

	private String sex;

}
