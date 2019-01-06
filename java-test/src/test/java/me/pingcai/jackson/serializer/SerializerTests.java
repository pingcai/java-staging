package me.pingcai.jackson.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.api.pipe.SyncStartForAsyncFeature;
import me.pingcai.jackson.entity.Position;
import me.pingcai.jackson.entity.Staff;
import me.pingcai.jackson.entity.ViewMode;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangpingcai
 * @since 2019/1/4 16:11
 */
public class SerializerTests {

    private static ObjectMapper mapper = new ObjectMapper();
    private static Staff staff = new Staff();

    {
        staff.setId(1L);
        staff.setName("tom");
        staff.setPosition(Position.ADMIN);
        staff.setSalary(1000000L);
        staff.setBirthday(new Date());
    }

    @Test
    public void testPrettyPrint() throws JsonProcessingException {
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
        System.out.println(json);
    }

    @Test
    public void testView() throws JsonProcessingException {
        staff.setSubStaff(Lists.newArrayList(Staff.build("jerry"), Staff.build("leo")));
        String json = mapper.writerWithView(ViewMode.Normal.class).writeValueAsString(staff);
        System.out.println(json);
    }

    @Test
    public void testGenericType() throws IOException {
        String json = mapper.writeValueAsString(staff);
        System.out.println("before: " + json);
        Staff newStaff = mapper.readValue(json, Staff.class);
        System.out.println(newStaff);
        System.out.println("after: " + mapper.writeValueAsString(newStaff));
    }

    @Test
    public void testGenericTypeArray() throws IOException {
        String array = "[{\"id\":1,\"name\":\"tom\",\"level\":1},{\"id\":1,\"name\":\"tom\",\"level\":1}]";
        //List<Staff> staffList = mapper.readValue(array, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Staff.class));
        List<Staff> staffList = mapper.readValue(array, new TypeReference<List<Staff>>(){});
        System.out.println(staffList);
    }


}
