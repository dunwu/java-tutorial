package io.github.dunwu.javalib.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author Zhang Peng
 * @since 2019-03-18
 */
public class FastjsonAnnotationBean {

    private int id;

    // 配置date序列化和反序列使用yyyyMMdd日期格式
    @JSONField(format = "yyyy-MM-dd")
    private Date date1;

    // 不序列化
    @JSONField(serialize = false)
    private Date date2;

    // 不反序列化
    @JSONField(deserialize = false)
    private Date date3;

    // 按ordinal排序
    @JSONField(ordinal = 2)
    private int f1;

    @JSONField(ordinal = 1)
    private int f2;

    public FastjsonAnnotationBean() {
    }

    public FastjsonAnnotationBean(int id, Date date1, Date date2, Date date3, int f1, int f2) {
        this.id = id;
        this.date1 = date1;
        this.date2 = date2;
        this.date3 = date3;
        this.f1 = f1;
        this.f2 = f2;
    }

    @JSONField(name = "ID")
    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private Date getDate1() {
        return date1;
    }

    private void setDate1(Date date1) {
        this.date1 = date1;
    }

    private Date getDate2() {
        return date2;
    }

    private void setDate2(Date date2) {
        this.date2 = date2;
    }

    private Date getDate3() {
        return date3;
    }

    private void setDate3(Date date3) {
        this.date3 = date3;
    }

    private int getF1() {
        return f1;
    }

    private void setF1(int f1) {
        this.f1 = f1;
    }

    private int getF2() {
        return f2;
    }

    private void setF2(int f2) {
        this.f2 = f2;
    }

}
