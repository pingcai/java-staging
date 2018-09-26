package me.pingcai.dao.enums;

/**
 * @author huangpingcai
 * @since 2018/9/26 11:09
 */
public enum OrderStatus implements DbEnum {

    CREATED(0, "新建状态"),
    CANCEL(1,"已取消"),
    TO_DELIVERY(4,"待配送"),
    DONE(8,"已完成");

    private Integer dbValue;

    private String desc;

    private OrderStatus(Integer dbValue, String desc) {
        this.dbValue = dbValue;
        this.desc = desc;
    }

    @Override
    public Integer getDbValue() {
        return dbValue;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatus of(Integer dbValue) {
        for (OrderStatus status : values()) {
            if (status.getDbValue().equals(dbValue)) {
                return status;
            }
        }
        return null;
    }

}
