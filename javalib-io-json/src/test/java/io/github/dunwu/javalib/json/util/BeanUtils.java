package io.github.dunwu.javalib.json.util;

import io.github.dunwu.javalib.json.TestBean;
import io.github.dunwu.javalib.json.TestBean2;
import io.github.dunwu.javalib.json.bean.Group;
import io.github.dunwu.javalib.json.bean.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class BeanUtils {

    public static Group initGroupBean() {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);

        return group;
    }

    public static TestBean initJdk8Bean() {
        String[] strArray = { "a", "b", "c" };
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(intArray));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 18);
        map.put("length", 175.3f);
        TestBean bean = new TestBean();
        Date date = Date.valueOf("2019-11-22");
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
        LocalDate localDate = LocalDate.of(1949, 10, 1);
        bean.setI1(10).setI2(1024).setF1(0.5f).setD1(100.0)
            .setDate1(date).setDate2(localDateTime).setDate3(localDate)
            .setColor(TestBean.Color.BLUE).setStrArray(strArray).setIntList(intList).setMap(map);
        return bean;
    }

    public static TestBean2 initNotJdk8Bean() {
        String[] strArray = { "a", "b", "c" };
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(intArray));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 18);
        map.put("length", 175.3f);
        TestBean2 bean = new TestBean2();
        Date date = Date.valueOf("2019-11-22");
        bean.setI1(10).setI2(1024).setF1(0.5f).setD1(100.0)
            .setDate1(date)
            .setColor(TestBean2.Color.BLUE).setStrArray(strArray).setIntList(intList).setMap(map);
        return bean;
    }

}
