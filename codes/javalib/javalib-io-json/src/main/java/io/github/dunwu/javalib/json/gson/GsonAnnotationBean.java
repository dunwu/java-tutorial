package io.github.dunwu.javalib.json.gson;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2019-11-24
 */
public class GsonAnnotationBean {

    @SerializedName("custom_naming")
    private String someField;

    private String someOtherField;

    @Override
    public int hashCode() {
        return Objects.hash(someField, someOtherField);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GsonAnnotationBean)) {
            return false;
        }
        GsonAnnotationBean that = (GsonAnnotationBean) o;
        return Objects.equals(someField, that.someField) &&
            Objects.equals(someOtherField, that.someOtherField);
    }

    public String getSomeField() {
        return someField;
    }

    public void setSomeField(String someField) {
        this.someField = someField;
    }

    public String getSomeOtherField() {
        return someOtherField;
    }

    public void setSomeOtherField(String someOtherField) {
        this.someOtherField = someOtherField;
    }

}
