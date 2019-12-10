package io.github.dunwu.javalib.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 定义一个满足大多数情况的 Bean 结构（含 JDK8 数据类型），使得各种 Json 库测试性能时能相对公平
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TestBean {

    private int i1;

    private Integer i2;

    private float f1;

    private Double d1;

    private Date date1;

    private LocalDateTime date2;

    private LocalDate date3;

    private Color color;

    private String[] strArray;

    private List<Integer> intList;

    private Map<String, Object> map;

    public static enum Color {
        RED,
        YELLOW,
        BLUE
    }

}
