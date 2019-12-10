package io.github.dunwu.javalib.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-24
 */
public class GsonCaseTests {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private Gson gson2 = new GsonBuilder()
        .setVersion(1.0)
        .setPrettyPrinting()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE)
        .create();

    @Test
    public void test() {
        // Serialization
        Gson gson = new Gson();
        gson.toJson(1);            // ==> 1
        gson.toJson("abcd");       // ==> "abcd"
        gson.toJson(10L); // ==> 10
        int[] values = { 1 };
        gson.toJson(values);       // ==> [1]

        // Deserialization
        int i1 = gson.fromJson("1", int.class);
        Integer i2 = gson.fromJson("1", Integer.class);
        Long l1 = gson.fromJson("1", Long.class);
        Boolean b1 = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);

        assertThat(i1).isEqualTo(1);
        assertThat(i2).isEqualTo(1);
        assertThat(l1).isEqualTo(1L);
        assertThat(b1).isFalse();
        assertThat(str).isEqualTo("abc");
    }

    @Test
    public void testAnnotation() {
        GsonAnnotationBean oldBean = new GsonAnnotationBean();
        oldBean.setSomeField("hello");
        oldBean.setSomeOtherField("world");

        String expectStr = "{\"custom_naming\":\"hello\",\"someOtherField\":\"world\"}";
        String json = gson.toJson(oldBean);
        assertThat(json).isEqualTo(expectStr);

        GsonAnnotationBean newBean = gson.fromJson(expectStr, GsonAnnotationBean.class);
        assertThat(newBean).isEqualTo(oldBean);
    }

    @Test
    public void testVersionedClass() {
        VersionedClass versionedObject = new VersionedClass();
        String jsonOutput = gson2.toJson(versionedObject);
        System.out.println(jsonOutput);
        assertThat(jsonOutput).isEqualTo("{\n"
            + "  \"newField\": \"new\",\n"
            + "  \"field\": \"old\"\n"
            + "}");

        jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
        assertThat(jsonOutput).isEqualTo("{\"newerField\":\"newer\",\"newField\":\"new\",\"field\":\"old\"}");
    }

}
