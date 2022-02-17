package io.github.dunwu.javatech.bean;

import cn.hutool.core.bean.BeanUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-05-15
 */
public class BeanConvertTest {

    @Test
    public void convertTest() {
        Person person = new Person();
        person.setName("Jack").setAge(20).setSex(Sex.MALE);
        // Bean 转换
        User user = BeanUtil.toBean(person, User.class);
        Assertions.assertEquals(person.getName(), user.getName());
        Assertions.assertEquals(person.getAge(), user.getAge());
        Assertions.assertEquals(person.getSex().name(), user.getSex());
    }

    public enum Sex {
        MALE,
        FEMALE
    }

    static class User {

        private String name;

        private Integer age;

        private String sex;

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public User setAge(Integer age) {
            this.age = age;
            return this;
        }

        public String getSex() {
            return sex;
        }

        public User setSex(String sex) {
            this.sex = sex;
            return this;
        }

    }

    static class Person {

        private String name;

        private Integer age;

        private Sex sex;

        public String getName() {
            return name;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public Person setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Sex getSex() {
            return sex;
        }

        public Person setSex(Sex sex) {
            this.sex = sex;
            return this;
        }

    }

}
