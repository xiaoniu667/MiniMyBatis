package com.xiaoniu.builder;

import com.xiaoniu.Configuration;
import com.xiaoniu.builder.xml.XMLConfigBuilder;
import com.xiaoniu.session.SqlSessionFactory;
import com.xiaoniu.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();
        return build(configuration);
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
