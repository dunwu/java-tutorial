package io.github.dunwu.javatech.support;

public class JdkHashCodeStrategy implements HashStrategy {

    @Override
    public int hashCode(String key) {
        return key.hashCode();
    }

}
