package io.github.dunwu.distributed.support;

public class JdkHashCodeStrategy implements HashStrategy {

    @Override
    public int hashCode(String key) {
        return key.hashCode();
    }

}
