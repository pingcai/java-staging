package me.pingcai.util;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author huangpingcai
 * @since 2019-05-19 00:57
 */
public class DateUtilsTest {

    @Test
    public void secondBetween() {
        DateTime now = DateTime.now();
        Assert.assertEquals(61, DateUtils.secondBetween(now.toDate(), now.minusMinutes(1).minusSeconds(1).minusMillis(111).toDate()));
    }

    @Test
    public void minuteBetween() {
        DateTime now = DateTime.now();
        Assert.assertEquals(120, DateUtils.minuteBetween(now.toDate(), now.minusHours(1).minusMinutes(59).minusSeconds(99).toDate()));
    }

    @Test
    public void hourBetween() {
        DateTime now = DateTime.now();
        Assert.assertEquals(47, DateUtils.hourBetween(now.toDate(), now.minusDays(1).minusHours(23).minusMinutes(59).toDate()));
    }

    @Test
    public void dayBetween() {
        DateTime now = DateTime.now();
        Assert.assertEquals(500, DateUtils.dayBetween(now.toDate(), now.minusDays(500).minusHours(23).minusMinutes(59).toDate()));
    }

    @Test
    public void format() {
        DateTime now = DateTime.now();
        Assert.assertEquals(now.toString(DateUtils.DEFAULT_DATETIME_PATTERN), DateUtils.format(now.toDate()));
    }

    @Test
    public void format1() {
        DateTime now = DateTime.now();
        Assert.assertEquals(now.toString(DateUtils.DEFAULT_DATETIME_PATTERN), DateUtils.format(now.toDate(), DateUtils.DEFAULT_DATETIME_PATTERN));
    }

    @Test
    public void parse() {
        DateTime now = DateTime.now().withTimeAtStartOfDay();
        Assert.assertEquals(now.toDate(), DateUtils.parse(now.toString(DateUtils.DEFAULT_DATETIME_PATTERN)));
    }

    @Test
    public void parse1() {
        DateTime now = DateTime.now().withTimeAtStartOfDay();
        Assert.assertEquals(now.toDate(), DateUtils.parse(now.toString(DateUtils.DEFAULT_DATETIME_PATTERN), DateUtils.DEFAULT_DATETIME_PATTERN));
    }
}