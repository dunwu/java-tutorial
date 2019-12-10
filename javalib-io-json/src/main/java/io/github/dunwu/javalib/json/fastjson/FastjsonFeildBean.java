package io.github.dunwu.javalib.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

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
public class FastjsonFeildBean {

    private int id;

    // 配置date序列化和反序列使用yyyyMMdd日期格式
    @JSONField(format = "yyyyMMdd")
    private Date date1;

    // 不序列化
    @JSONField(serialize = false, format = "yyyy-MM-dd hh:mm:ss")
    private LocalDate date2;

    // 不反序列化
    @JSONField(deserialize = false, format = "yyyy-MM-dd")
    private LocalDateTime date3;

    @JSONField(ordinal = 1)
    private Double d1;

    // 按ordinal排序
    @JSONField(ordinal = 2)
    private float f1;

    public FastjsonFeildBean() {
    }

    public FastjsonFeildBean(int id, Date date1, LocalDate date2, LocalDateTime date3, float f1, Double d1) {
        this.id = id;
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
        this.f1 = f1;
        this.d1 = d1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date1, date2, date3, f1, d1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FastjsonFeildBean)) {
            return false;
        }
        FastjsonFeildBean that = (FastjsonFeildBean) o;
        return id == that.id &&
            Float.compare(that.f1, f1) == 0 &&
            Objects.equals(date1, that.date1) &&
            Objects.equals(date2, that.date2) &&
            Objects.equals(date3, that.date3) &&
            Objects.equals(d1, that.d1);
    }

    @Override
    public String toString() {
        return "FastjsonAnnotationBean{" +
            "id=" + id +
            ", date1=" + date1 +
            ", date2=" + date2 +
            ", date3=" + date3 +
            ", f1=" + f1 +
            ", f2=" + d1 +
            '}';
    }

    @JSONField(name = "ID")
    public int getId() {
        return id;
    }

    @JSONField(name = "ID")
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }

    public LocalDateTime getDate3() {
        return date3;
    }

    public void setDate3(LocalDateTime date3) {
        this.date3 = date3;
    }

    public float getF1() {
        return f1;
    }

    public void setF1(float f1) {
        this.f1 = f1;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }

}
