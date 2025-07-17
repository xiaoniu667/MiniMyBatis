package com.xiaoniu.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypeHandler extends BaseTypeHandler<Object>
{

    @Override
    protected void setTypeParameter(PreparedStatement ps, int i, Object value) throws SQLException {
        ps.setObject(i, value);
    }

    @Override
    public Object getResult(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getObject(columnName);
    }
}
