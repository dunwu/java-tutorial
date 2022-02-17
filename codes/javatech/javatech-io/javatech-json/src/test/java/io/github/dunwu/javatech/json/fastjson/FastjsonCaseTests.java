package io.github.dunwu.javatech.json.fastjson;

import com.alibaba.fastjson.JSON;
import io.github.dunwu.javatech.json.util.Group;
import io.github.dunwu.javatech.json.util.DateUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-22
 */
public class FastjsonCaseTests {

    @Test
    @DisplayName("测试 Fastjson 默认序列化、反序列化")
    public void defaultTest() {
        Group oldGroup = new Group();
        String jsonString = JSON.toJSONString(oldGroup);

        System.out.println(jsonString);

        Group newGroup = JSON.parseObject(jsonString, Group.class);
        assertThat(newGroup).isNotNull();
    }

    /**
     * 序列化测试
     */
    @Test
    @DisplayName("测试 JavaBean 上使用 @JSONField 注解后的序列化、反序列化")
    public void serializeTest() {
        LocalDate localDate = LocalDate.of(1949, 10, 1);
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
        Date date = DateUtil.toDate(localDateTime);

        FastjsonAnnotationBean originBean =
            new FastjsonAnnotationBean(1, date, date, localDate, localDateTime, 100.0, 0.5f, 1000);
        final String expectJson =
            "{\"ID\":1,\"date1\":\"2000-01-01\",\"date2\":946699200000,\"date4\":\"2000-01-01\",\"d1\":100.0,\"f2\":1000,\"f1\":0.5}";
        String json = JSON.toJSONString(originBean);
        Assertions.assertEquals(expectJson, json);
        System.out.println("json = [" + json + "]");

        FastjsonAnnotationBean targetBean = JSON.parseObject(json, FastjsonAnnotationBean.class);
        FastjsonAnnotationBean expectBean = new FastjsonAnnotationBean();
        expectBean.setId(1).setDate1(date).setDate4(localDateTime).setD1(100.0).setF1(0.5f);
        System.out.printf("deserialize result: %s", targetBean.toString());
        Assertions.assertNotEquals(originBean, targetBean);
    }

}
