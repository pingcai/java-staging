package me.pingcai.dao.enums;

/**
 * 整形(TINYINT / INT)都应用Integer接收, bit可以用Byte接收
 * @author huangpingcai
 * @since 2018/8/24 23:12
 */
public enum  UserStatus implements DbEnum<Integer>{
    /*尚未激活*/
    NONACTIVATED(0),

    /*正常*/
    NORMAL(1),

    /*冻结*/
    FREEZE(2),

    /*已注销*/
    DELETED(3)
    ;

    private Integer dbValue;

    UserStatus(Integer dbValue) {
        this.dbValue = dbValue;
    }

    @Override
    public Integer getDbValue() {
        return dbValue;
    }

}
