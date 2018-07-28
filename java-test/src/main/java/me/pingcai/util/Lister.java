package me.pingcai.util;

import java.util.List;

public class Lister {
    public static <T> T firstOfList(List<T> list){
        return list == null || list.isEmpty() ? null : list.get(0);
    }
}
