package io.github.dunwu.javalib.bean;

import lombok.Data;

import java.util.List;

/**
 * Lombok 示例
 *
 * @see http://jnb.ociweb.com/jnb/jnbJan2010.html
 * @author Zhang Peng
 */
@Data(staticConstructor = "of")
public class Company {

	private final Person founder;
	protected List<Person> employees;
	private String name;

}
