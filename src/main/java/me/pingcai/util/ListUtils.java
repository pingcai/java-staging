package me.pingcai.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListUtils {

    public static boolean isEmpty(Collection collection) {
        return collection != null && collection.isEmpty();
    }

    public static <T> T firstOfList(List<T> list) {
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public static List safeList(List list) {
        return list == null ? Collections.EMPTY_LIST : list;
    }

    public static String join(String splitter, List<?> list) {
        if (isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : list) {
            sb.append(obj)
                    .append(splitter);
        }
        if (StringUtils.isEmpty(splitter)) {
            return sb.toString();
        }
        return sb.substring(0, sb.length() - splitter.length());
    }
}
