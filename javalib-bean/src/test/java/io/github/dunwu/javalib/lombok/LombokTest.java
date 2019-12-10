package io.github.dunwu.javalib.lombok;

import lombok.Cleanup;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Lombok 单元测试
 *
 * @author Zhang Peng
 * @see <a href="http://jnb.ociweb.com/jnb/jnbJan2010.html">Reducing Boilerplate Code with Project Lombok</a>
 */
public class LombokTest {

    @Test
    public void testGetterAndSetterDemo() {
        GetterAndSetterDemo demo = new GetterAndSetterDemo();
        demo.setEmployed(true);
        demo.setName("xxx");
        assertThat(demo.isEmployed()).isTrue();
    }

    @Test
    public void testToStringDemo() {
        ToStringDemo demo = new ToStringDemo(true, "abc", 0.5f);
        System.out.println(demo.toString());
        assertThat(demo.toString()).contains("someBoolean=true, someStringField=abc");

        Person person = new Person();
        person.setName("张三");
        person.setAge(20);
        person.setSex("男");
        System.out.println(person.toString());
        assertThat(person.toString()).isEqualTo("Person(name=张三, sex=男)");
    }

    @Test
    public void testEqualsAndHashCodeDemo() {
        EqualsAndHashCodeDemo demo1 =
            new EqualsAndHashCodeDemo("name1", EqualsAndHashCodeDemo.Gender.Female, "ssn", "xxx", "xxx", "xxx", "xxx");
        EqualsAndHashCodeDemo demo2 =
            new EqualsAndHashCodeDemo("name1", EqualsAndHashCodeDemo.Gender.Female, "ssn", "ooo", "ooo", "ooo", "ooo");
        assertThat(demo1.equals(demo2)).isTrue();

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

        assertThat(person.equals(person2)).isTrue();
        assertThat(person.equals(person3)).isFalse();
    }

    @Test
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
        assertThat(demo.getName()).isEqualTo("黑心农产品公司");
        assertThat(demo.getFounder()).isEqualTo(huangshiren);

        System.out.println("公司名：" + demo.getName());
        System.out.println("创始人：" + demo.getFounder());
        System.out.println("员工信息");
        assertThat(demo.getEmployees()).contains(yangbailao, xiaobaicai);
        demo.getEmployees().forEach(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
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

}
