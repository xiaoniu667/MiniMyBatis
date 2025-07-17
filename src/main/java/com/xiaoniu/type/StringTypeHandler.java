package com.xiaoniu.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringTypeHandler extends BaseTypeHandler<String>{



    @Override
    protected void setTypeParameter(PreparedStatement ps, int i, String value) throws SQLException {
        ps.setString(i, value);
    }

    @Override
    public String getResult(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getString(columnName);
    }
}
