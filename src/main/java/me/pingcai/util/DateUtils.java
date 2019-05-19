package me.pingcai.util;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

/**
 * create by 黄平财 at 2018/1/6 00:35
 */
public final class DateUtils {

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    private DateUtils() {
    }

    public static long unixTimestamp() {
        return System.currentTimeMillis();
    }

    public static int unixTimestampSecond() {
        return (int) (System.currentTimeMillis() / 1000);
    }


    /**
     * @param d1 第一个时间
     * @param d2 第二个时间
     * @return 两个时间间隔的秒数，忽略毫秒、微秒、纳秒
     */
    public static long secondBetween(Date d1, Date d2) {
        return Duration.millis(d1.getTime() - d2.getTime()).getStandardSeconds();
    }

    public static long minuteBetween(Date d1, Date d2) {
        return Duration.millis(d1.getTime() - d2.getTime()).getStandardMinutes();
    }

    public static long hourBetween(Date d1, Date d2) {
        return Duration.millis(d1.getTime() - d2.getTime()).getStandardHours();
    }

    public static long dayBetween(Date d1, Date d2) {
        return Duration.millis(d1.getTime() - d2.getTime()).getStandardDays();
    }


    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String format(Date date) {
        return Optional.ofNullable(date)
                .map(d -> format(d, DEFAULT_DATETIME_PATTERN))
                .orElse(null);
    }

    public static String format(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }


    /**
     * 解析时间
     * @param date
     * @return
     */
    public static Date parse(String date) {
        return Optional.ofNullable(date)
                .map(d -> parse(d, DEFAULT_DATETIME_PATTERN))
                .orElse(null);
    }

    public static Date parse(String date, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(date).toDate();
    }

}
