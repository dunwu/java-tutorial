package io.github.dunwu.javatech.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * <code>@JSONField</code> 的使用
 * <p>
 * <code>@JSONField</code> 可以配置在 getter/setter 方法上
 * <p>
 * <code>@JSONField</code> 可以配置在字段上，但是要求字段必须是 public
 *
 * @author Zhang Peng
 * @see <a href="https://github.com/alibaba/fastjson/wiki/JSONField"><code>@JSONField</code></a>
 * @since 2019-03-18
 */
public class FastjsonAnnotationBean implements Serializable {

    private int id;

    // 配置date序列化和反序列使用yyyyMMdd日期格式
    @JSONField(format = "yyyy-MM-dd")
    private Date date1;

    @JsonIgnore
    private Date date2;

    // 不序列化
    @JSONField(serialize = false, format = "yyyy-MM-dd hh:mm:ss")
    private LocalDate date3;

    // 不反序列化
    @JSONField(deserialize = false, format = "yyyy-MM-dd")
    private LocalDateTime date4;

    @JSONField(ordinal = 1)
    private Double d1;

    // 按ordinal排序
    @JSONField(ordinal = 2)
    private float f1;

    @JSONField(ordinal = 1)
    private int f2;

    public FastjsonAnnotationBean() {
    }

    public FastjsonAnnotationBean(int id, Date date1, Date date2, LocalDate date3, LocalDateTime date4, Double d1,
        float f1,
        int f2) {
        this.id = id;
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
        this.date4 = date4;
        this.d1 = d1;
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date1, date2, date3, date4, d1, f1, f2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FastjsonAnnotationBean)) return false;
        FastjsonAnnotationBean that = (FastjsonAnnotationBean) o;
        return id == that.id &&
            Float.compare(that.f1, f1) == 0 &&
            f2 == that.f2 &&
            Objects.equals(date1, that.date1) &&
            Objects.equals(date2, that.date2) &&
            Objects.equals(date3, that.date3) &&
            Objects.equals(date4, that.date4) &&
            Objects.equals(d1, that.d1);
    }

    @Override
    public String toString() {
        return "FastjsonAnnotationBean{" +
            "id=" + id +
            ", date1=" + date1 +
            ", date2=" + date2 +
            ", date3=" + date3 +
            ", date4=" + date4 +
            ", d1=" + d1 +
            ", f1=" + f1 +
            ", f2=" + f2 +
            '}';
    }

    @JSONField(name = "ID")
    public int getId() {
        return id;
    }

    public FastjsonAnnotationBean setId(int id) {
        this.id = id;
        return this;
    }

    public Date getDate1() {
        return date1;
    }

    public FastjsonAnnotationBean setDate1(Date date1) {
        this.date1 = date1;
        return this;
    }

    public Date getDate2() {
        return date2;
    }

    public FastjsonAnnotationBean setDate2(Date date2) {
        this.date2 = date2;
        return this;
    }

    public LocalDate getDate3() {
        return date3;
    }

    public FastjsonAnnotationBean setDate3(LocalDate date3) {
        this.date3 = date3;
        return this;
    }

    public LocalDateTime getDate4() {
        return date4;
    }

    public FastjsonAnnotationBean setDate4(LocalDateTime date4) {
        this.date4 = date4;
        return this;
    }

    public Double getD1() {
        return d1;
    }

    public FastjsonAnnotationBean setD1(Double d1) {
        this.d1 = d1;
        return this;
    }

    public float getF1() {
        return f1;
    }

    public FastjsonAnnotationBean setF1(float f1) {
        this.f1 = f1;
        return this;
    }

    public int getF2() {
        return f2;
    }

    public FastjsonAnnotationBean setF2(int f2) {
        this.f2 = f2;
        return this;
    }

}
