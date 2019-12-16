package io.github.dunwu.javalib.office;

import com.github.promeg.pinyinhelper.Pinyin;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-12-09
 */
public class PinyinDemo {

    public static void main(String[] args) {
        System.out.println("args = " + Pinyin.toPinyin('中'));
    }

}
