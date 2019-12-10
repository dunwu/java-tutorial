package io.github.dunwu.javalib.lombok;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * <code>@NonNull</code> 示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="http://jnb.ociweb.com/jnb/jnbJan2010.html#nonnull">@NonNull</a>
 * @since 2019-11-22
 */
public class NonNullDemo {

    @Getter
    @Setter
    @NonNull
    private List<Person> members;

}
