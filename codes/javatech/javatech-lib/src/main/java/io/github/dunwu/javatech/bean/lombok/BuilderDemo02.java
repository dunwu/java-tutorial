package io.github.dunwu.javatech.bean.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修正 {@link BuilderDemo01} 使用不当的问题
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @date 2020/12/3
 * @see BuilderDemo01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuilderDemo02 {

    private String name;

    public static void main(String[] args) throws JsonProcessingException {
        BuilderDemo02 demo02 = BuilderDemo02.builder().name("demo01").build();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(demo02);
        BuilderDemo02 expectDemo02 = mapper.readValue(json, BuilderDemo02.class);
        System.out.println(expectDemo02.toString());
    }

}
