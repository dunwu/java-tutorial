package io.github.dunwu.javalib.bean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import lombok.Cleanup;

/**
 * Lombok 单元测试
 * @see @see http://jnb.ociweb.com/jnb/jnbJan2010.html
 * @author Zhang Peng
 */
public class LombokTest {
    @Test
    public void testData() {
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

        Company company = Company.of(huangshiren);
        company.setName("黑心农产品公司");
        company.setEmployees(personList);

        System.out.println("公司名：" + company.getName());
        System.out.println("创始人：" + company.getFounder());
        System.out.println("员工信息");
        company.getEmployees().forEach(person -> {
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

    @Test
    public void testToString() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(20);
        person.setSex("男");
        System.out.println(person.toString());
        // output: Person(name=张三, sex=男)
    }

    @Test
    public void testEqualsAndHashCode() {
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

        Assert.assertEquals(person, person2);
        Assert.assertNotEquals(person, person3);
    }
}
