package io.github.dunwu.javatech.bean.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

/**
 * 使用 @Builder 不当导致 json 反序列化失败
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2020/12/3
 */
@Data
@Builder
public class BuilderDemo01 {

    private String name;

    public static void main(String[] args) throws JsonProcessingException {
        BuilderDemo01 demo01 = BuilderDemo01.builder().name("demo01").build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(demo01);
        BuilderDemo01 expectDemo01 = mapper.readValue(json, BuilderDemo01.class);
        System.out.println(expectDemo01.toString());
    }

}
