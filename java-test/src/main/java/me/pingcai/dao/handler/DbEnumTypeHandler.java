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
public class DbEnumTypeHandler<E extends Enum<?> & DbEnum> extends BaseTypeHandler<DbEnum<?>> {

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
    public void setNonNullParameter(PreparedStatement ps, int i, DbEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i,parameter.getDbValue());
    }

    @Override
    public DbEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object dbValue = rs.getObject(columnName);
        return rs.wasNull() ? null : getEnum(dbValue);
    }

    @Override
    public DbEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object dbValue = rs.getObject(columnIndex);
        return rs.wasNull() ? null : getEnum(dbValue);
    }

    @Override
    public DbEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object dbValue = cs.getObject(columnIndex);
        return cs.wasNull() ? null : getEnum(dbValue);
    }

    private E getEnum(Object dbValue){
        try {
            E[] enumConstants = type.getEnumConstants();
            for (E e : enumConstants) {
                if (Objects.equals(e.getDbValue(),dbValue)){
                    return e;
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + dbValue + " to " + type.getSimpleName() + " by code value.", ex);
        }
        return null;
    }
}
