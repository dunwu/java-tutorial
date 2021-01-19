package io.github.dunwu.javatech;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * @author peng.zhang
 * @date 2021/1/19
 */
public class IpHashLoadBalance<N extends Node> extends BaseLoadBalance<N> implements LoadBalance<N> {

    @Override
    protected N doSelect(List<N> nodes, String ip) {
        if (StrUtil.isBlank(ip)) {
            ip = "127.0.0.1";
        }

        int length = nodes.size();
        int index = hash(ip) % length;
        return nodes.get(index);
    }

    public int hash(String text) {
        return HashUtil.fnvHash(text);
    }

}
