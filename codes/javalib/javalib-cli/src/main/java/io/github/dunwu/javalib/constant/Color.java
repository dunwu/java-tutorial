package io.github.dunwu.javalib.constant;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019/10/30
 */
public enum Color {

    DEFAULT("默认"),
    BLACK("黑色"),
    RED("红色"),
    GREEN("绿色"),
    YELLOW("黄色"),
    BLUE("蓝色"),
    MAGENTA("紫色"),
    CYAN("青色"),
    WHITE("白色"),
    BRIGHT_BLACK("亮黑色"),
    BRIGHT_RED("亮红色"),
    BRIGHT_GREEN("亮绿色"),
    BRIGHT_YELLOW("亮黄色"),
    BRIGHT_BLUE("亮蓝色"),
    BRIGHT_MAGENTA("亮紫色"),
    BRIGHT_CYAN("亮青色"),
    BRIGHT_WHITE("亮白色");

    private String desc;

    Color(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
