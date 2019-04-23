package me.pingcai.dao.enums;

/**
 * 整形(TINYINT / INT)都应用Integer接收, bit可以用Byte接收
 * @author huangpingcai
 * @since 2018/8/24 23:12
 */
public enum  UserStatus implements DbEnum{
    NONACTIVATED(0,"尚未激活"),

    NORMAL(1,"正常"),

    FREEZE(2,"已冻结"),

    DELETED(3,"已注销")
    ;

    private Integer dbValue;

    private String desc;

    UserStatus(Integer dbValue, String desc) {
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

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
