package me.pingcai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Maps;
import lombok.Data;
import me.pingcai.util.JsonUtils;
import org.junit.Test;
import org.omg.CORBA.MARSHAL;
import org.omg.CORBA.OBJ_ADAPTER;

import java.time.LocalDate;
import java.time.ZoneId;
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

    Map<String,Object> map;

    List<Object> list;

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    @Test
    public void test() {
        System.out.println(System.currentTimeMillis());
        LocalDate c = LocalDate.parse("2017-11-27", FORMATTER);
        c.toEpochDay();
        System.out.println(c.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
    }

    @Test
    public void testJson() throws JsonProcessingException {
        AppTests tests = new AppTests();
        tests.setName("tom");
        tests.setMap(Maps.newHashMap());
        tests.getMap().put("test","test");
        tests.getMap().put("test2","test2");
        tests.getMap().put("test2", Arrays.asList("abc","dce"));
        tests.getMap().put("test3", 2);
        tests.setList(Arrays.asList("adb",234,false));

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
