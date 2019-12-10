package io.github.dunwu.javalib.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dunwu.javalib.bean.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Jackson 使用示例
 *
 * @author Zhang Peng
 * @since 2019-03-18
 */
public class JacksonTests {

    final ObjectMapper mapper = new ObjectMapper();

    /**
     * 序列化测试
     */
    @Test
    public void serialize() {
        Person p = new Person("Tom", 20);
        String json = null;
        try {
            json = mapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(json);
        System.out.println("json = [" + json + "]");
    }

    /**
     * 反序列化测试
     */
    @Test
    public void deserialize() {
        final String json = "{\"age\":20,\"name\":\"Tom\"}";
        Person p = null;
        try {
            p = mapper.readValue(json, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(p);
        System.out.println("p = [" + p + "]");
    }

    /**
     * 序列化测试
     */
    @Test
    public void serialize2() {
        Person p = new Person("Tom", 20);
        Person p2 = new Person("Jack", 22);
        Person p3 = new Person("Mary", 18);

        List<Person> persons = new LinkedList<>();
        persons.add(p);
        persons.add(p2);
        persons.add(p3);

        Map<String, List> map = new HashMap<>();
        map.put("persons", persons);

        String json = null;
        try {
            json = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(json);
        System.out.println("json = [" + json + "]");
    }

    /**
     * 序列化测试
     */
    @Test
    public void serialize3() {
        JacksonAnnotationBean jacksonAnnotationBean = new JacksonAnnotationBean("jack", 19, "男");
        String json = null;
        try {
            json = mapper.writeValueAsString(jacksonAnnotationBean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(json);
        System.out.println("json = [" + json + "]");
    }

}
