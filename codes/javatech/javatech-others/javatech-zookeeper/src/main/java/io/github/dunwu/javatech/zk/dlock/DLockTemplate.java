package io.github.dunwu.javatech.zk.dlock;

/**
 * 分布式锁模板类 Created by sunyujia@aliyun.com on 2016/2/23.
 */
public interface DLockTemplate {

    /**
     * @param lockId   锁id(对应业务唯一ID)
     * @param timeout  单位毫秒
     * @param callback 回调函数
     * @return
     */
    <V> V execute(String lockId, long timeout, Callback<V> callback);

}
