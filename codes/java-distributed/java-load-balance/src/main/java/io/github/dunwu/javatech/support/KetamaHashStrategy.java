package io.github.dunwu.javatech.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KetamaHashStrategy implements HashStrategy {

    private static MessageDigest md5Digest;

    static {
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
    }

    @Override
    public int hashCode(String key) {
        byte[] bKey = computeMd5(key);
        long rv = ((long) (bKey[3] & 0xFF) << 24)
            | ((long) (bKey[2] & 0xFF) << 16)
            | ((long) (bKey[1] & 0xFF) << 8)
            | (bKey[0] & 0xFF);
        return (int) (rv & 0xffffffffL);
    }

    /**
     * Get the md5 of the given key.
     */
    public static byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = (MessageDigest) md5Digest.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("clone of MD5 not supported", e);
        }
        md5.update(k.getBytes());
        return md5.digest();
    }

}
