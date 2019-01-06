package me.pingcai.jackson.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author huangpingcai
 * @since 2019/1/4 16:14
 */
public enum Position {
    ADMIN(1, "管理员"),
    STAFF(2, "普通员工");

    private int value;
    private String desc;

    Position(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @JsonCreator
    public static final Position of(int id) {
        for (Position position : values()) {
            if (position.value == id) {
                return position;
            }
        }
        return null;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
