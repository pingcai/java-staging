package me.pingcai.domain.enums;

/**
 * 整形(TINYINT / INT)都应用Integer接收, bit可以用Byte接收
 * @author huangpingcai
 * @since 2018/8/24 23:12
 */
public enum UserSex implements DbEnum{
    /*男*/
    MALE(0),

    /*女*/
    FEMALE(1),

    UNKNOWN(2);

    private Integer dbValue;

    UserSex(Integer dbValue) {
        this.dbValue = dbValue;
    }

    UserSex(int i) {
        dbValue = i;
    }

    @Override
    public Integer getDbValue() {
        return dbValue;
    }

}
