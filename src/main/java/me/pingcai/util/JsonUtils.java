package me.pingcai.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * create by 黄平财 at 2018/1/5 17:25
 */
public final class JsonUtils {

    private static ObjectMapper MAPPER = getInstance(true);

    private static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd hh:mm:ss";

    private JsonUtils() {
    }

    /**
     * @param create 是否创建新的ObjectMapper对象
     * @return
     */
    public static ObjectMapper getInstance(boolean create) {
        return create ? newInstance() : MAPPER;
    }

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String object2Json(Object object) {
        String res = null;
        if (Objects.nonNull(object)) {
            try {
                res = MAPPER.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

    /**
     * 字符串转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        T res = null;
        try {
            res = MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    private static ObjectMapper newInstance() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_PATTERN));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}
