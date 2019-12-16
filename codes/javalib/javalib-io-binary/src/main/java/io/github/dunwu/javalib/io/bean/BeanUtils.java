package io.github.dunwu.javalib.io.bean;

import io.github.dunwu.util.time.DateExtUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class BeanUtils {

    public static TestBean initJdk8Bean() {
        String[] strArray = { "a", "b", "c" };
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 18);
        map.put("length", 175.3f);
        TestBean bean = new TestBean();
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
        Date date = DateExtUtils.localDateTime2Date(localDateTime);
        LocalDate localDate = LocalDate.of(1949, 10, 1);
        bean.setI1(10).setI2(1024).setF1(0.5f).setD1(100.0)
            .setDate1(date).setDate2(localDateTime).setDate3(localDate)
            .setColor(TestBean.Color.BLUE).setStrArray(strArray).setIntList(intList).setMap(map);
        return bean;
    }

}
