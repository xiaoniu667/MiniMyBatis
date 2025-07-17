package com.xiaoniu.datasource;

import javax.sql.DataSource;
import java.util.Properties;

public interface DataSourceFactory {
    void setProperty(Properties property);
    DataSource getDataSource();
}
