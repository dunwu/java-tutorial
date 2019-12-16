package io.github.dunwu.javalib.constant;

/**
 * SGR (Select Graphic Rendition) 设置显示属性。
 * <p>
 * 可以按相同的顺序设置多个属性，并用分号隔开。
 * <p>
 * 每个显示属性一直有效，直到随后发生SGR重置它为止。
 * <p>
 * 如果未给出代码，则将CSI m视为CSI 0 m（重置/正常）。
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#SGR_parameters">SGR</a>
 * @since 2019/10/30
 */
public enum AnsiSgr implements AnsiElement {

    NORMAL("0"),
    BOLD("1"),
    FAINT("2"),
    ITALIC("3"),
    UNDERLINE("4"),
    SLOW_BLINK("5"),
    RAPID_BLINK("6"),
    REVERSE_VIDEO("7"),
    CONCEAL("8");

    private final String code;

    AnsiSgr(String code) {
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
