package me.pingcai.dao.handler;

import me.pingcai.dao.enums.DbEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author huangpingcai
 * @since 2018/8/24 23:24
 */
public class DbEnumTypeHandler<E extends Enum<E> & DbEnum> extends BaseTypeHandler<E> {

    private Class<E> type;

    public DbEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("db enum type cannot be null");
        }
        if (!DbEnum.class.isAssignableFrom(type)){
            throw new UnsupportedOperationException("type argument must be implementation of DbEnum");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getDbValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int dbValue = rs.getInt(columnName);
        return rs.wasNull() ? null : toEnum(dbValue);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int dbValue = rs.getInt(columnIndex);
        return rs.wasNull() ? null : toEnum(dbValue);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int dbValue = cs.getInt(columnIndex);
        return cs.wasNull() ? null : toEnum(dbValue);
    }

    private E toEnum(int dbValue){
        try {
            E[] enumConstants = type.getEnumConstants();
            for (E e : enumConstants) {
                if (Objects.equals(e.getDbValue(),dbValue)){
                    return e;
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("cannot convert " + dbValue + " to " + type.getSimpleName(), ex);
        }
        return null;
    }
}
