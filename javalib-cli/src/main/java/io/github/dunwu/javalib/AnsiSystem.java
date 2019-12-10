package io.github.dunwu.javalib;

import io.github.dunwu.javalib.constant.AnsiBgColor;
import io.github.dunwu.javalib.constant.AnsiColor;
import io.github.dunwu.javalib.constant.AnsiSgr;
import io.github.dunwu.javalib.constant.Color;
import org.apache.commons.lang3.StringUtils;

/**
 * 以 Ansi 方式在控制台输出（输出彩色字体、粗体、斜体、下划线等）
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI escape code</a>
 * @since 2019/10/30
 */
public class AnsiSystem {

    public static final AnsiSystem RED = new AnsiSystem("\033[;31m");

    public static final AnsiSystem GREEN = new AnsiSystem("\033[;32m");

    public static final AnsiSystem YELLOW = new AnsiSystem("\033[;33m");

    public static final AnsiSystem BLUE = new AnsiSystem("\033[;34m");

    public static final AnsiSystem MAGENTA = new AnsiSystem("\033[;35m");

    public static final AnsiSystem CYAN = new AnsiSystem("\033[;36m");

    public static final AnsiSystem WHITE = new AnsiSystem("\033[;37m");

    private static final String ENCODE_JOIN = ";";

    private static final String ENCODE_START = "\033[";

    private static final String ENCODE_END = "m";

    private static final String RESET = "\033[0;m";

    private String code;

    public AnsiSystem(String code) {
        this.code = code;
    }

    public AnsiSystem(AnsiConfig config) {
        this.code = encode(config);
    }

    private String encode(AnsiConfig config) {
        StringBuilder sb = new StringBuilder();
        sb.append(ENCODE_START);
        if (config.isBold()) {
            sb.append(ENCODE_JOIN).append(AnsiSgr.BOLD.getCode());
        }
        if (config.isItalic()) {
            sb.append(ENCODE_JOIN).append(AnsiSgr.ITALIC.getCode());
        }
        if (config.isUnderline()) {
            sb.append(ENCODE_JOIN).append(AnsiSgr.UNDERLINE.getCode());
        }
        if (config.isSlowBlink()) {
            sb.append(ENCODE_JOIN).append(AnsiSgr.SLOW_BLINK.getCode());
        } else {
            if (config.isRapidBlink()) {
                sb.append(ENCODE_JOIN).append(AnsiSgr.RAPID_BLINK.getCode());
            }
        }
        if (config.isReverseVideo()) {
            sb.append(ENCODE_JOIN).append(AnsiSgr.REVERSE_VIDEO.getCode());
        }
        if (config.isCanceal()) {
            sb.append(ENCODE_JOIN).append(AnsiSgr.CONCEAL.getCode());
        }
        if (config.getColor() != null) {
            AnsiColor color = AnsiColor.valueOf(config.getColor().name());
            if (StringUtils.isNotBlank(color.getCode())) {
                sb.append(ENCODE_JOIN).append(color.getCode());
            }
        }
        if (config.getBgColor() != null) {
            AnsiBgColor color = AnsiBgColor.valueOf(config.getBgColor().name());
            if (StringUtils.isNotBlank(color.getCode())) {
                sb.append(ENCODE_JOIN).append(color.getCode());
            }
        }
        sb.append(ENCODE_END);
        return sb.toString();
    }

    public void print(String message) {
        System.out.print(code + message + RESET);
    }

    public void println(String message) {
        System.out.println(code + message + RESET);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Ansi 配置
     */
    public static class AnsiConfig {

        private boolean bold;

        private boolean italic;

        private boolean underline;

        private boolean slowBlink;

        private boolean rapidBlink;

        private boolean reverseVideo;

        private boolean canceal;

        private Color color;

        private Color bgColor;

        public AnsiConfig() {
            this.bold = false;
            this.italic = false;
            this.underline = false;
            this.slowBlink = false;
            this.rapidBlink = false;
            this.reverseVideo = false;
            this.canceal = false;
            this.color = Color.DEFAULT;
            this.bgColor = Color.DEFAULT;
        }

        public AnsiConfig(boolean bold, boolean italic, boolean underline, boolean slowBlink, boolean rapidBlink,
            boolean reverseVideo, boolean canceal, Color color, Color bgColor) {
            this.bold = bold;
            this.italic = italic;
            this.underline = underline;
            this.slowBlink = slowBlink;
            this.rapidBlink = rapidBlink;
            this.reverseVideo = reverseVideo;
            this.canceal = canceal;
            this.color = color;
            this.bgColor = bgColor;
        }

        @Override
        public String toString() {
            return "AnsiParam{" +
                "bold=" + bold +
                ", italic=" + italic +
                ", underline=" + underline +
                ", slowBlink=" + slowBlink +
                ", rapidBlink=" + rapidBlink +
                ", reverseVideo=" + reverseVideo +
                ", canceal=" + canceal +
                ", color=" + color +
                ", bgColor=" + bgColor +
                '}';
        }

        public boolean isBold() {
            return bold;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }

        public boolean isItalic() {
            return italic;
        }

        public void setItalic(boolean italic) {
            this.italic = italic;
        }

        public boolean isUnderline() {
            return underline;
        }

        public void setUnderline(boolean underline) {
            this.underline = underline;
        }

        public boolean isSlowBlink() {
            return slowBlink;
        }

        public void setSlowBlink(boolean slowBlink) {
            this.slowBlink = slowBlink;
        }

        public boolean isRapidBlink() {
            return rapidBlink;
        }

        public void setRapidBlink(boolean rapidBlink) {
            this.rapidBlink = rapidBlink;
        }

        public boolean isReverseVideo() {
            return reverseVideo;
        }

        public void setReverseVideo(boolean reverseVideo) {
            this.reverseVideo = reverseVideo;
        }

        public boolean isCanceal() {
            return canceal;
        }

        public void setCanceal(boolean canceal) {
            this.canceal = canceal;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getBgColor() {
            return bgColor;
        }

        public void setBgColor(Color bgColor) {
            this.bgColor = bgColor;
        }

    }

}
