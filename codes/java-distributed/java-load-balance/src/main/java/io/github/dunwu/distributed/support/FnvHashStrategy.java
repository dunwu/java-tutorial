package io.github.dunwu.distributed.support;

public class FnvHashStrategy implements HashStrategy {

    private static final long FNV_32_INIT = 2166136261L;

    private static final int FNV_32_PRIME = 16777619;

    @Override
    public int hashCode(String key) {
        final int p = FNV_32_PRIME;
        int hash = (int) FNV_32_INIT;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        hash = Math.abs(hash);
        return hash;
    }

}
