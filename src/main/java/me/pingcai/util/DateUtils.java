package me.pingcai.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * create by 黄平财 at 2018/1/6 00:35
 */
public final class DateUtils {

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_PATTERN);

    private DateUtils() {
    }

    public static String format(Date date) {
        return format(date, DEFAULT_DATETIME_PATTERN);
    }

    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(TemporalAccessor date) {
        return DEFAULT_DATETIME_FORMATTER.format(date);
    }

    public static Date parse(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DEFAULT_DATETIME_FORMATTER);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
