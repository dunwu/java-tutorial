package io.github.dunwu.javatech.json.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="mailto:forbreak@163.com">Zhang Peng</a>
 * @since 2020-04-08
 */
public class DateUtil extends cn.hutool.core.date.DateUtil {

    /**
     * 将 {@link Calendar} 转化为 {@link Date}
     *
     * @param calendar {@link Calendar}
     * @return {@link Date}
     */
    public static Date toDate(final Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * 将 {@link LocalDateTime} 转化为 {@link Date}
     *
     * @param localDateTime {@link LocalDateTime}
     * @return {@link Date}
     */
    public static Date toDate(final LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static String formatDurationString(Duration duration) {
        Duration temp = duration;
        StringBuilder sb = new StringBuilder();

        long days = temp.toDays();
        if (days > 0) {
            sb.append(days + "d ");
        }
        temp = temp.minusDays(days);

        long hours = temp.toHours();
        if (hours > 0) {
            sb.append(hours + "h ");
        }
        temp = temp.minusHours(hours);

        long minutes = temp.toMinutes();
        if (minutes > 0) {
            sb.append(minutes + "m ");
        }
        temp = temp.minusMinutes(minutes);

        long seconds = temp.getSeconds();
        if (seconds > 0) {
            sb.append(minutes + "s ");
        }
        temp = temp.minusSeconds(seconds);

        long millis = temp.toMillis();
        if (millis > 0) {
            sb.append(millis + "ms ");
        }
        temp = temp.minusMillis(millis);

        long nanos = temp.toNanos();
        if (nanos > 0) {
            sb.append(nanos + "ns ");
        }
        return sb.toString();
    }

    public static String formatDurationChineseString(Duration duration) {
        Duration temp = duration;
        StringBuilder sb = new StringBuilder();

        long days = temp.toDays();
        if (days > 0) {
            sb.append(days + "天 ");
        }
        temp = temp.minusDays(days);

        long hours = temp.toHours();
        if (hours > 0) {
            sb.append(hours + "时 ");
        }
        temp = temp.minusHours(hours);

        long minutes = temp.toMinutes();
        if (minutes > 0) {
            sb.append(minutes + "分 ");
        }
        temp = temp.minusMinutes(minutes);

        long seconds = temp.getSeconds();
        if (seconds > 0) {
            sb.append(minutes + "秒 ");
        }
        temp = temp.minusSeconds(seconds);

        long millis = temp.toMillis();
        if (millis > 0) {
            sb.append(millis + "毫秒 ");
        }
        temp = temp.minusMillis(millis);

        long nanos = temp.toNanos();
        if (nanos > 0) {
            sb.append(nanos + "纳秒 ");
        }
        return sb.toString();
    }

}
