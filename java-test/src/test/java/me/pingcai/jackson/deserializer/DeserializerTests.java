package me.pingcai.jackson.deserializer;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.pingcai.jackson.entity.Staff;
import org.junit.Test;

import java.io.IOException;

/**
 * @author huangpingcai
 * @since 2019/1/4 16:22
 */
public class DeserializerTests {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testJsonCreator() throws IOException {
        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"tom\",\n" +
                "    \"desc\": null,\n" +
                "    \"level\": 1,\n" +
                "    \"birthday\": \"2019-01-04 09:20:08\",\n" +
                "    \"unknownProperties\": {\n" +
                "        \"comment\": \"测试\"\n" +
                "    }\n" +
                "}";
        Staff staff = mapper.readValue(json, Staff.class);
        System.out.println(staff);
    }



}
