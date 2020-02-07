package io.github.dunwu.javatech;

public class StatisticsUtil {

    // 方差 s^2=[(x1-x)^2 +...(xn-x)^2]/n
    public static double variance(Long[] array) {
        int m = array.length;
        double sum = 0;
        for (Long item : array) {// 求和
            sum += item;
        }
        double avg = sum / m;// 求平均值
        double value = 0;
        for (Long item : array) {// 求方差
            value += (item - avg) * (item - avg);
        }
        return value / m;
    }

    // 标准差σ=sqrt(s^2)
    public static double standardDeviation(Long[] array) {
        int m = array.length;
        double sum = 0;
        for (Long item : array) {// 求和
            sum += item;
        }
        double avg = sum / m;// 求平均值
        double value = 0;
        for (Long item : array) {// 求方差
            value += (item - avg) * (item - avg);
        }
        return Math.sqrt(value / m);
    }

}
