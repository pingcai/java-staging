package me.pingcai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.Data;
import me.pingcai.util.JsonUtils;
import org.junit.Test;

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
