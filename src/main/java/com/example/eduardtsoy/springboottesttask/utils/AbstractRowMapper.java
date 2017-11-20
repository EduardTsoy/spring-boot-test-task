package com.example.eduardtsoy.springboottesttask.utils;

import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class AbstractRowMapper<T> implements RowMapper<T> {

    public static Long getLong(@Nonnull final ResultSet rs,
                               @Nonnull final String columnName) throws SQLException {
        final Long value = rs.getLong(columnName);
        return !rs.wasNull() ? value : null;
    }

    public static String getString(@Nonnull final ResultSet rs,
                                   @Nonnull final String columnName) throws SQLException {
        final String value = rs.getString(columnName);
        return !rs.wasNull() ? value : null;
    }

    public static BigDecimal getBigDecimal(@Nonnull final ResultSet rs,
                                           @Nonnull final String columnName) throws SQLException {
        final BigDecimal value = rs.getBigDecimal(columnName);
        return !rs.wasNull() ? value : null;
    }
}
