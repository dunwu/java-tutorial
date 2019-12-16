package io.github.dunwu.javalib.json.fastjson;

import com.alibaba.fastjson.JSON;
import io.github.dunwu.javalib.bean.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Fastjson 使用示例
 *
 * @author Zhang Peng
 * @since 2019-03-18
 */
public class FastjsonTests {

    /**
     * 序列化测试
     */
    @Test
    public void serialize() {
        Person p = new Person("Tom", 20);
        String json = JSON.toJSONString(p);
        Assert.assertNotNull(json);
        System.out.println("json = [" + json + "]");
    }

    /**
     * 反序列化测试
     */
    @Test
    public void deserialize() {
        final String json = "{\"age\":20,\"name\":\"Tom\"}";
        Person p = JSON.parseObject(json, Person.class);
        Assert.assertNotNull(p);
        System.out.println("p = [" + p + "]");
    }

    /**
     * 序列化测试
     */
    @Test
    public void serializeAnnotation() {
        FastjsonAnnotationBean bean = new FastjsonAnnotationBean(1, new Date(), new Date(), new Date(), 10, 20);
        String json = JSON.toJSONString(bean);
        Assert.assertNotNull(json);
        System.out.println("json = [" + json + "]");
    }

}
