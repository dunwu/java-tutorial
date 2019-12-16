package io.github.dunwu.javalib.json.fastjson;

import com.alibaba.fastjson.JSON;
import io.github.dunwu.javalib.json.bean.Group;
import io.github.dunwu.javalib.json.util.BeanUtils;
import io.github.dunwu.util.time.DateExtUtils;
import org.junit.Test;

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
    public void test() {
        Group oldGroup = BeanUtils.initGroupBean();
        String jsonString = JSON.toJSONString(oldGroup);
        System.out.println(jsonString);
        Group newGroup = JSON.parseObject(jsonString, Group.class);
        assertThat(newGroup).isNotNull();
    }

    /**
     * 序列化测试
     */
    @Test
    public void serialize() {
        LocalDate localDate = LocalDate.of(1949, 10, 1);
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
        Date date = DateExtUtils.localDateTime2Date(localDateTime);
        FastjsonFeildBean bean = new FastjsonFeildBean(1, date, localDate, localDateTime, 0.5f, 100.0);
        String json = JSON.toJSONString(bean);
        assertThat(json).isEqualTo(
            "{\"ID\":1,\"date1\":\"20000101\",\"date3\":\"2000-01-01\",\"d1\":100.0,\"f1\":0.5}");
        System.out.println("json = [" + json + "]");
    }

    /**
     * 反序列化测试
     */
    @Test
    public void deserialize() {
        final String json = "{\"ID\":1,\"date1\":\"20000101\",\"date3\":\"2000-01-01\",\"d1\":100.0,\"f1\":0.5}";
        FastjsonFeildBean actualBean = JSON.parseObject(json, FastjsonFeildBean.class);
        System.out.printf("deserialize result: %s", actualBean.toString());
        assertThat(actualBean).isNotNull();
    }

}
