package io.github.dunwu.javatech.bean.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lombok 单元测试
 *
 * @author Zhang Peng
 * @see <a href="http://jnb.ociweb.com/jnb/jnbJan2010.html">Reducing Boilerplate Code with Project Lombok</a>
 */
public class LombokTest {

    @Test
    @DisplayName("测试 @Getter / @Setter")
    public void testGetterAndSetterDemo() {
        GetterAndSetterDemo demo = new GetterAndSetterDemo();
        demo.setEmployed(true);
        demo.setName("xxx");
        Assertions.assertTrue(demo.isEmployed());
    }

    @Test
    @DisplayName("测试 @ToString")
    public void testToStringDemo() {
        ToStringDemo demo = new ToStringDemo(true, "abc", 0.5f);
        System.out.println(demo.toString());

        String str = demo.toString();
        Assertions.assertTrue(str.contains("someBoolean=true, someStringField=abc"));

        Person person = new Person();
        person.setName("张三");
        person.setAge(20);
        person.setSex("男");
        System.out.println(person.toString());
        Assertions.assertEquals("Person(name=张三, sex=男)", person.toString());
    }

    @Test
    @DisplayName("测试 @EqualsAndHashCode")
    public void testEqualsAndHashCodeDemo() {
        EqualsAndHashCodeDemo demo1 =
            new EqualsAndHashCodeDemo("name1", EqualsAndHashCodeDemo.Gender.Female, "ssn", "xxx", "xxx", "xxx", "xxx");
        EqualsAndHashCodeDemo demo2 =
            new EqualsAndHashCodeDemo("name1", EqualsAndHashCodeDemo.Gender.Female, "ssn", "ooo", "ooo", "ooo", "ooo");
        Assertions.assertEquals(demo1, demo2);

        Person person = new Person();
        person.setName("张三");
        person.setAge(20);
        person.setSex("男");

        Person person2 = new Person();
        person2.setName("张三");
        person2.setAge(18);
        person2.setSex("男");

        Person person3 = new Person();
        person3.setName("李四");
        person3.setAge(20);
        person3.setSex("男");

        Assertions.assertEquals(person2, person);
        Assertions.assertNotEquals(person3, person);
    }

    @Test
    @DisplayName("测试 @Data")
    public void testDataDemo() {
        Person huangshiren = new Person();
        huangshiren.setName("黄世仁");
        huangshiren.setAge(30);
        huangshiren.setSex("男");
        Person yangbailao = new Person();
        yangbailao.setName("杨白劳");
        yangbailao.setAge(50);
        yangbailao.setSex("男");
        Person xiaobaicai = new Person();
        xiaobaicai.setName("小白菜");
        xiaobaicai.setAge(20);
        xiaobaicai.setSex("女");

        List<Person> personList = new ArrayList<>();
        personList.add(yangbailao);
        personList.add(xiaobaicai);

        DataDemo demo = DataDemo.of(huangshiren);
        demo.setName("黑心农产品公司");
        demo.setEmployees(personList);
        Assertions.assertEquals("黑心农产品公司", demo.getName());
        Assertions.assertEquals(huangshiren, demo.getFounder());

        System.out.println("公司名：" + demo.getName());
        System.out.println("创始人：" + demo.getFounder());
        System.out.println("员工信息");

        List<Person> employees = demo.getEmployees();
        Assertions.assertTrue(employees.containsAll(Arrays.asList(yangbailao, xiaobaicai)));
        demo.getEmployees().forEach(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    @DisplayName("测试 @Cleanup")
    public void testCleanUp() {
        try {
            @Cleanup
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(new byte[] { 'Y', 'e', 's' });
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("测试 @Builder")
    public void testBuilder() {
        BuilderDemo01 demo01 = BuilderDemo01.builder().name("demo01").build();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(demo01);
            BuilderDemo01 expectDemo01 = mapper.readValue(json, BuilderDemo01.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
