package me.pingcai;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * create by 黄平财 at 2017/11/30 23:23
 */
public class AppTests {
    String name;

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    @Test
    public void test() {
        System.out.println(System.currentTimeMillis());
        LocalDate c = LocalDate.parse("2017-11-27", FORMATTER);
        c.toEpochDay();
        System.out.println(c.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
    }
}
