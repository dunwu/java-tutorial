package io.github.dunwu.javatech.java.samples;

/**
 * 验证码类型枚举
 *
 * @author peng.zhang
 * @date 2021-09-24
 */
public enum CaptchaTypeEnum {
    /**
     * 算数
     */
    ARITHMETIC(1),
    /**
     * 中文
     */
    CHINESE(2),
    /**
     * 中文闪图
     */
    CHINESE_GIF(3),
    /**
     * 闪图
     */
    GIF(4),
    /**
     * 数字大写字母
     */
    SPEC(5);

    private final int code;

    CaptchaTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
