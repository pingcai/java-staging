package me.pingcai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.Data;
import me.pingcai.util.JsonUtils;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;
import java.time.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * create by 黄平财 at 2017/11/30 23:23
 */
@Data
public class AppTests {
    String name;

    Map<String, Object> map;

    List<Object> list;

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Test
    public void testXJson() throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("name")
                .field("type").value("text")
                .startObject("fields")
                .startObject("keyword")
                .field("ignore_above").value(256)
                .field("type").value("keyword")
                .endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        xContentBuilder.humanReadable(true);

        System.out.println(xContentBuilder.string());
    }

    @Test
    public void test() {

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime after = LocalDateTime.now().plusDays(10).minusYears(2).plusHours(12).plusSeconds(12).plusMonths(2).minusMinutes(2).minusNanos(323);

        Period period = Period.between(after.toLocalDate(), now.toLocalDate());

        System.out.println(period.getDays());


        Duration duration = Duration.between(now, after);

        System.out.println(duration.toNanos() / 1000);


    }

    @Test
    public void testZone() {

        ZoneId zoneId = ZoneId.of("Europe/London");

        //System.out.println(ZoneId.getAvailableZoneIds());

        System.out.println(ZonedDateTime.now(ZoneId.of("Europe/London")));

        System.out.println(ZonedDateTime.now());


        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);

        System.out.println(zoneOffset);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        OffsetDateTime offsetDateTime;
    }

    @Test
    public void testYearMonth() {

        YearMonth yearMonth = YearMonth.now();

        System.out.println(yearMonth.isLeapYear());


        yearMonth.isLeapYear();
        yearMonth.lengthOfMonth();
        yearMonth.lengthOfYear();
        System.out.println(yearMonth.atEndOfMonth());

        MonthDay monthDay = MonthDay.now();

        System.out.println(monthDay);

        MonthDay newMonth = monthDay.with(Month.JANUARY);

        System.out.println(newMonth.getDayOfMonth());

        System.out.println(newMonth);


    }

    @Test
    public void testParse() {

        LocalDate birthday = LocalDate.of(1995, 7, 20);

        LocalDate today = LocalDate.now();

        MonthDay bir = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());

        if (bir.equals(MonthDay.of(7, 20))) {
            System.out.println("birthday");
        }

        Period period = Period.between(birthday, today);


        System.out.println(Instant.ofEpochSecond(birthday.atTime(12, 12).toEpochSecond(ZoneOffset.UTC)));
        System.out.println(Instant.ofEpochSecond(birthday.atTime(12, 12).toEpochSecond(ZoneOffset.of("+8"))));

        System.out.println(Instant.ofEpochMilli(1).atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public void testDatetime2Timestamp() {
        System.out.println(System.currentTimeMillis());
        LocalDate date = LocalDate.parse("2017-11-27", FORMATTER);
        LocalDateTime dateTime = date.atStartOfDay();
        System.out.println(dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    @Test
    public void testJson() throws JsonProcessingException {
        AppTests tests = new AppTests();
        tests.setName("tom");
        tests.setMap(Maps.newHashMap());
        tests.getMap().put("test", "test");
        tests.getMap().put("test2", "test2");
        tests.getMap().put("test2", Arrays.asList("abc", "dce"));
        tests.getMap().put("test3", 2);
        tests.setList(Arrays.asList("adb", 234, false));

        ObjectMapper mapper = JsonUtils.getInstance(true);

        String res = mapper.writeValueAsString(tests);

        System.out.println(res);

        AppTests test2 = JsonUtils.json2ObjectWithTypeReference(res, new TypeReference<AppTests>() {
            @Override
            public int compareTo(TypeReference<AppTests> o) {
                return super.compareTo(o);
            }
        });

        System.out.println(test2);
    }
}
