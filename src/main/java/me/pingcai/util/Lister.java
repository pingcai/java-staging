package me.pingcai.util;

import java.util.Collections;
import java.util.List;

public class Lister {
    public static <T> T firstOfList(List<T> list) {
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public static List safeList(List list) {
        return null == list ? Collections.EMPTY_LIST : list;
    }
}
