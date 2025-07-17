package com.xiaoniu.mapping;


public interface SqlSource {

  BoundSql getBoundSql(Object parameterObject);

}
