package io.github.dunwu.javalib.bean;

import java.util.List;
import lombok.Data;

/**
 * Lombok 示例
 *
 * @author Zhang Peng
 * @see http://jnb.ociweb.com/jnb/jnbJan2010.html
 */
@Data(staticConstructor = "of")
public class Company {

	private final Person founder;

	protected List<Person> employees;

	private String name;

}
