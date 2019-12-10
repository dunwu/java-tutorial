package io.github.dunwu.javalib.json.fastjson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Zhang Peng
 * @since 2019-03-18
 */
@JsonPropertyOrder(alphabetic = true)
public class JacksonAnnotationBean {

    private String Name;

    private int Age;

    @JsonIgnore
    private String Sex;

    public JacksonAnnotationBean() {
    }

    public JacksonAnnotationBean(String name, int age, String sex) {
        Name = name;
        Age = age;
        Sex = sex;
    }

    @JsonProperty("username")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

}
