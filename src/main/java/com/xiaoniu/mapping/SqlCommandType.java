package com.xiaoniu.mapping;

import org.omg.CORBA.UNKNOWN;

//枚举类用于判断sql语句类型
public enum SqlCommandType {
    UNKNOWN,
    INSERT,
    UPDATE,
    DELETE,
    SELECT,
    FLUSH;

    private SqlCommandType() {
    }
}
