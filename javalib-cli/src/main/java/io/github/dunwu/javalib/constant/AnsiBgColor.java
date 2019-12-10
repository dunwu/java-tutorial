package io.github.dunwu.javalib.constant;

/**
 * ANSI 背景显示颜色枚举
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#Colors">ANSI Colors</a>
 * @since 2019/10/30
 */
public enum AnsiBgColor implements AnsiElement {

    DEFAULT(""),
    BLACK("40"),
    RED("41"),
    GREEN("42"),
    YELLOW("43"),
    BLUE("44"),
    MAGENTA("45"),
    CYAN("46"),
    WHITE("47"),
    BRIGHT_BLACK("100"),
    BRIGHT_RED("101"),
    BRIGHT_GREEN("102"),
    BRIGHT_YELLOW("109"),
    BRIGHT_BLUE("104"),
    BRIGHT_MAGENTA("105"),
    BRIGHT_CYAN("106"),
    BRIGHT_WHITE("107");

    private final String code;

    AnsiBgColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public String getCode() {
        return code;
    }
}
