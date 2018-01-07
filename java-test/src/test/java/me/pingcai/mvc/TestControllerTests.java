package me.pingcai.mvc;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.util.HttpClientUtils;
import me.pingcai.util.JsonUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * create by 黄平财 at 2018/1/7 13:07
 */
@Slf4j
public class TestControllerTests{

    @Test
    public void testInsert() throws IOException {

        // curl -X POST -d '{"id":1,"name":"tom","age":12}' -H 'content-type:application/json' http://127.0.0.1:7878/insert

        String url = Constant.HTTP_LOCALHOST + "/insert";

        Map<String,Object> data = Maps.newHashMap();
        data.put("name","tom");
        data.put("age",12);

        String json = JsonUtils.object2Json(data);

        log.info("param : {}",json);
        String res = HttpClientUtils.postJson(url,null,json);
        log.info("response : {}",res);


    }
}
