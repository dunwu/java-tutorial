package io.github.dunwu.javalib.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

class Parent {}

/**
 * <code>@EqualsAndHashCode</code> 示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="http://jnb.ociweb.com/jnb/jnbJan2010.html#equals">@EqualsAndHashCode</a>
 * @since 2019-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "address", "city", "state", "zip" })
public class EqualsAndHashCodeDemo extends Person {

    @NonNull
    private String name;

    @NonNull
    private Gender gender;

    private String ssn;

    private String address;

    private String city;

    private String state;

    private String zip;

    public EqualsAndHashCodeDemo(@NonNull String name, @NonNull Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public EqualsAndHashCodeDemo(@NonNull String name, @NonNull Gender gender,
        String ssn, String address, String city, String state, String zip) {
        this.name = name;
        this.gender = gender;
        this.ssn = ssn;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    enum Gender {
        Male,
        Female
    }

}
