package io.github.dunwu.javatech.cache;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

/**
 * Memcached 客户端连接示例
 *
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-07-10
 */
public class MemcachedDemo {

    public static final String URL = "127.0.0.1";
    public static final int PORT = 11211;

    public static void main(String[] args) {
        add();
        remove();
        append();
        prepend();
        cas();
        get();
        delete();
        incrAndDecr();
    }

    public static void add() {
        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数据
            Future fo = mcc.set("MyKey", 900, "Free Education");

            // 打印状态
            System.out.println("set status:" + fo.get());

            // 输出
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 添加
            fo = mcc.add("MyKey", 900, "memcached");

            // 打印状态
            System.out.println("add status:" + fo.get());

            // 添加新key
            fo = mcc.add("codingground", 900, "All Free Compilers");

            // 打印状态
            System.out.println("add status:" + fo.get());

            // 输出
            System.out.println("codingground value in cache - " + mcc.get("codingground"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void remove() {

        try {
            //连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加第一个 key=》value 对
            Future fo = mcc.set("MyKey", 900, "Free Education");

            // 输出执行 add 方法后的状态
            System.out.println("add status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 添加新的 key
            fo = mcc.replace("MyKey", 900, "Largest Tutorials' Library");

            // 输出执行 set 方法后的状态
            System.out.println("replace status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void append() {

        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数据
            Future fo = mcc.set("MyKey", 900, "Free Education");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 对存在的key进行数据添加操作
            fo = mcc.append(900, "MyKey", " for All");

            // 输出执行 set 方法后的状态
            System.out.println("append status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("codingground"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void prepend() {

        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数据
            Future fo = mcc.set("MyKey", 900, "Education for All");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 对存在的key进行数据添加操作
            fo = mcc.prepend(900, "MyKey", "Free ");

            // 输出执行 set 方法后的状态
            System.out.println("prepend status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("codingground"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void cas() {

        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数据
            Future fo = mcc.set("MyKey", 900, "Free Education");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 使用 get 方法获取数据
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 通过 gets 方法获取 CAS token（令牌）
            CASValue casValue = mcc.gets("MyKey");

            // 输出 CAS token（令牌） 值
            System.out.println("CAS token - " + casValue);

            // 尝试使用cas方法来更新数据
            CASResponse casresp = mcc.cas("MyKey", casValue.getCas(), 900, "Largest Tutorials-Library");

            // 输出 CAS 响应信息
            System.out.println("CAS Response - " + casresp);

            // 输出值
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void get() {

        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数据
            Future fo = mcc.set("MyKey", 900, "Free Education");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 使用 get 方法获取数据
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void delete() {

        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数据
            Future fo = mcc.set("MyKey", 900, "World's largest online tutorials library");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("MyKey"));

            // 对存在的key进行数据添加操作
            fo = mcc.delete("MyKey");

            // 输出执行 delete 方法后的状态
            System.out.println("delete status:" + fo.get());

            // 获取键对应的值
            System.out.println("MyKey value in cache - " + mcc.get("codingground"));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void incrAndDecr() {

        try {

            // 连接本地的 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress(URL, PORT));
            System.out.println("Connection to server sucessful.");

            // 添加数字值
            Future fo = mcc.set("number", 900, "1000");

            // 输出执行 set 方法后的状态
            System.out.println("set status:" + fo.get());

            // 获取键对应的值
            System.out.println("value in cache - " + mcc.get("number"));

            // 自增并输出
            System.out.println("value in cache after increment - " + mcc.incr("number", 111));

            // 自减并输出
            System.out.println("value in cache after decrement - " + mcc.decr("number", 112));

            // 关闭连接
            mcc.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
