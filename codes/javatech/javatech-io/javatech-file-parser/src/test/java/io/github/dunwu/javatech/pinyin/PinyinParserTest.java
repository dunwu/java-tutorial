package io.github.dunwu.javatech.pinyin;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;
import com.github.promeg.tinypinyin.lexicons.java.cncity.CnCityDict;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 汉字转拼音解析测试
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2022-02-07
 */
public class PinyinParserTest {

    @Test
    @DisplayName("测试一段文本")
    public void testText() {
        String text = "测试一段文本";
        String content = Pinyin.toPinyin(text, " ");
        System.out.println(content);
        Assertions.assertEquals("CE SHI YI DUAN WEN BEN", content);
    }

    @Test
    @DisplayName("测试中文城市词典拼音")
    public void testCityDict() {
        // 添加中文城市词典
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance()));

        // 添加自定义词典
        Pinyin.init(Pinyin.newConfig()
                          .with(new PinyinMapDict() {
                              @Override
                              public Map<String, String[]> mapping() {
                                  HashMap<String, String[]> map = new HashMap<>();
                                  map.put("重庆", new String[] { "CONG", "QIN" });
                                  return map;
                              }
                          }));

        String content = Pinyin.toPinyin("欢迎来重庆", " ");
        System.out.println(content);
        Assertions.assertEquals("HUAN YING LAI CONG QIN", content);
    }

}
