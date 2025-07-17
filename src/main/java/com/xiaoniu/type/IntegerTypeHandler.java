package com.xiaoniu.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerTypeHandler extends BaseTypeHandler<Integer> {


    @Override
    protected void setTypeParameter(PreparedStatement ps, int i, Integer value) throws SQLException {
        ps.setInt(i, value);
    }

    @Override
    public Integer getResult(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getInt(columnName);
    }
}
