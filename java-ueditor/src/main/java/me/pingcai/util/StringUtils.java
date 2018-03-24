package me.pingcai.util;

import java.util.UUID;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * @param removeLine 去除UUID中的横线
     * @return
     */
    public static String uuid(boolean removeLine) {
        String uuid = UUID.randomUUID().toString();
        return removeLine ? uuid.replaceAll("-", "") : uuid;
    }


}
