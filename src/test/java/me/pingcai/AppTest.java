package me.pingcai;

import me.pingcai.util.DateUtils;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * create by 黄平财 at 2017/11/30 23:23
 */
public class AppTest {
    @Test
    public void test() {
        System.out.println(DateTime.now().toString(DateUtils.DEFAULT_DATETIME_PATTERN));
    }
}
